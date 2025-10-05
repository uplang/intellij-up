package org.uplang.intellij

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object UpIcons {
    @JvmField
    val FILE: Icon = IconLoader.getIcon("/icons/up-file.svg", javaClass)

    @JvmField
    val LOGO: Icon = IconLoader.getIcon("/icons/up-logo.svg", javaClass)
}

