package org.uplang.intellij.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.uplang.intellij.lexer.UpLexerAdapter
import org.uplang.intellij.psi.UpTypes

class UpSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = UpLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            UpTypes.COMMENT -> COMMENT_KEYS
            UpTypes.KEY -> KEY_KEYS
            UpTypes.TYPE_ANNOTATION -> TYPE_KEYS
            UpTypes.STRING_VALUE -> STRING_KEYS
            UpTypes.NUMBER -> NUMBER_KEYS
            UpTypes.LBRACE, UpTypes.RBRACE -> BRACE_KEYS
            UpTypes.LBRACKET, UpTypes.RBRACKET -> BRACKET_KEYS
            UpTypes.MULTILINE_DELIMITER -> MULTILINE_KEYS
            UpTypes.NAMESPACE -> NAMESPACE_KEYS
            UpTypes.FUNCTION -> FUNCTION_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        val COMMENT = TextAttributesKey.createTextAttributesKey(
            "UP_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        )

        val KEY = TextAttributesKey.createTextAttributesKey(
            "UP_KEY",
            DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )

        val TYPE_ANNOTATION = TextAttributesKey.createTextAttributesKey(
            "UP_TYPE",
            DefaultLanguageHighlighterColors.METADATA
        )

        val STRING_VALUE = TextAttributesKey.createTextAttributesKey(
            "UP_STRING",
            DefaultLanguageHighlighterColors.STRING
        )

        val NUMBER = TextAttributesKey.createTextAttributesKey(
            "UP_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
        )

        val BRACE = TextAttributesKey.createTextAttributesKey(
            "UP_BRACE",
            DefaultLanguageHighlighterColors.BRACES
        )

        val BRACKET = TextAttributesKey.createTextAttributesKey(
            "UP_BRACKET",
            DefaultLanguageHighlighterColors.BRACKETS
        )

        val MULTILINE = TextAttributesKey.createTextAttributesKey(
            "UP_MULTILINE",
            DefaultLanguageHighlighterColors.STRING
        )

        val NAMESPACE = TextAttributesKey.createTextAttributesKey(
            "UP_NAMESPACE",
            DefaultLanguageHighlighterColors.CLASS_NAME
        )

        val FUNCTION = TextAttributesKey.createTextAttributesKey(
            "UP_FUNCTION",
            DefaultLanguageHighlighterColors.FUNCTION_CALL
        )

        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val KEY_KEYS = arrayOf(KEY)
        private val TYPE_KEYS = arrayOf(TYPE_ANNOTATION)
        private val STRING_KEYS = arrayOf(STRING_VALUE)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val BRACE_KEYS = arrayOf(BRACE)
        private val BRACKET_KEYS = arrayOf(BRACKET)
        private val MULTILINE_KEYS = arrayOf(MULTILINE)
        private val NAMESPACE_KEYS = arrayOf(NAMESPACE)
        private val FUNCTION_KEYS = arrayOf(FUNCTION)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}

