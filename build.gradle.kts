import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.intellij") version "1.16.1"
    id("org.jetbrains.changelog") version "2.2.0"
}

group = "org.uplang"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.redhat.devtools.lsp4ij:lsp4ij:0.0.1")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.5")
    type.set("IC") // Target IDE Platform: IntelliJ IDEA Community Edition

    plugins.set(listOf(
        "com.redhat.devtools.lsp4ij:0.0.1"
    ))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("241.*")

        // Extract the <!-- Plugin description --> section from README.md
        pluginDescription.set(
            file("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md")
                }
                subList(indexOf(start) + 1, indexOf(end))
                    .joinToString("\n")
                    .let { markdownToHTML(it) }
            }
        )

        changeNotes.set(provider {
            with(changelog) {
                renderItem(
                    getOrNull(version.get())
                        ?: getUnreleased()
                            .withHeader(false)
                            .withEmptySections(false),
                    Changelog.OutputType.HTML
                )
            }
        })
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

changelog {
    groups.empty()
    repositoryUrl.set("https://github.com/uplang/intellij-up")
}

