package org.uplang.intellij

import com.intellij.lang.Language

object UpLanguage : Language("UP") {
    private fun readResolve(): Any = UpLanguage

    override fun getDisplayName(): String = "UP"

    override fun isCaseSensitive(): Boolean = true
}

