package org.uplang.intellij.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.redhat.devtools.lsp4ij.LanguageServerEnablementSupport
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import org.uplang.intellij.UpFileType
import java.io.File

class UpLanguageServerSupportProvider : LanguageServerFactory, LanguageServerEnablementSupport {

    override fun createConnectionProvider(project: Project): StreamConnectionProvider {
        return UpLanguageServerConnectionProvider()
    }

    override fun createLanguageClient(project: Project): LanguageClientImpl {
        return LanguageClientImpl(project)
    }

    override fun isEnabled(file: VirtualFile, project: Project): Boolean {
        return file.fileType == UpFileType
    }
}

class UpLanguageServerConnectionProvider : StreamConnectionProvider {
    private var process: Process? = null

    override fun start() {
        val serverPath = findLanguageServer()
        if (serverPath != null) {
            val commands = listOf(serverPath)
            val processBuilder = ProcessBuilder(commands)
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
            process = processBuilder.start()
        } else {
            throw IllegalStateException(
                "UP Language Server not found. Please install: " +
                "go install github.com/uplang/tools/language-server@latest"
            )
        }
    }

    override fun getInputStream() = process?.inputStream

    override fun getOutputStream() = process?.outputStream

    override fun stop() {
        process?.destroy()
    }

    private fun findLanguageServer(): String? {
        // Try common locations
        val candidates = listOf(
            "up-language-server", // In PATH
            System.getProperty("user.home") + "/go/bin/up-language-server",
            "/usr/local/bin/up-language-server"
        )

        return candidates.firstOrNull { path ->
            try {
                val file = File(path)
                file.exists() && file.canExecute()
            } catch (e: Exception) {
                false
            }
        }
    }
}

