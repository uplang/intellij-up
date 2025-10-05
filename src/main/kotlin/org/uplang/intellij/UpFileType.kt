package org.uplang.intellij

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object UpFileType : LanguageFileType(UpLanguage) {
    override fun getName(): String = "UP"

    override fun getDescription(): String = "UP (Unified Properties) file"

    override fun getDefaultExtension(): String = "up"

    override fun getIcon(): Icon = UpIcons.FILE
}

