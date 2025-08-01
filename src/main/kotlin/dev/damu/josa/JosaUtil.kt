package dev.damu.josa

/**
 * Utility class for determining and appending the correct Korean postposition (josa)
 * based on phonetic rules of the last character in a word.
 */
object JosaUtil {
    enum class LetterCategory { KOREAN, NUMBER, ALPHABET, OTHER }

    private const val IGNORE_CHARS = "!?,.~'\";:{}[]()<>"
    private val BATCHIM_NUMBERS = setOf('0', '1', '3', '6', '7', '8')
    private val VOWELS = setOf('a', 'i', 'o', 'u')
    private val VOWELS_ALL = setOf('a', 'e', 'i', 'o', 'u')
    private val PREV_LMN = setOf('l', 'm', 'n')

    /**
     * Returns the input word with the appropriate [josa] appended.
     *
     * Examples:
     * - `"사람"` + `Josa.I_GA` → `"사람이"`
     * - `"친구"` + `Josa.I_GA` → `"친구가"`
     *
     * @param word the word to which the josa will be attached
     * @param josa the josa to append based on phonetic rules
     * @return the original word with the appropriate josa attached
     */
    fun withJosa(word: String, josa: Josa): String {
        val base = word.trim()
        return if (base.isEmpty()) base else base + resolveJosaForm(base, josa)
    }

    /**
     * Returns only the correct josa string (e.g., `"이"` or `"가"`) to be appended to the input [word].
     *
     * Examples:
     * - `"고양이"` + `Josa.EUN_NEUN` → `"는"`
     * - `"책"` + `Josa.EUN_NEUN` → `"은"`
     *
     * @param word the word to analyze
     * @param josa the josa to resolve
     * @return the correct josa string without the original word
     */
    fun findJosa(word: String, josa: Josa): String {
        return resolveJosaForm(word.trim(), josa)
    }

    /**
     * Internal core logic for determining the proper josa form.
     *
     * @param word trimmed word with punctuation removed
     * @param josa the josa to apply
     * @return either [Josa.withBatchim] or [Josa.withoutBatchim], depending on the word's ending
     */
    private fun resolveJosaForm(word: String, josa: Josa): String {
        val base = word.trim()
        if (base.isEmpty()) return josa.withoutBatchim

        val cleaned = base.trimEnd { it in IGNORE_CHARS }
        if (cleaned.isEmpty()) return josa.withoutBatchim

        val lastChar = cleaned.last()
        val hasBatchim = when (categoryOf(lastChar)) {
            LetterCategory.KOREAN   -> hasBatchimKorean(lastChar)
            LetterCategory.NUMBER   -> lastChar in BATCHIM_NUMBERS
            LetterCategory.ALPHABET -> hasBatchimAlphabet(cleaned.lowercase())
            else                    -> false
        }

        // Special case: if josa is '-으로' and ends with ㄹ, treat as no batchim
        if (josa.rieulEndsWithRo && categoryOf(lastChar) == LetterCategory.KOREAN) {
            val jong = (lastChar.code - '가'.code) % 28
            if (jong == 8) return josa.withoutBatchim
        }

        // Special case: '이에/에' → always use '에'
        if (josa == Josa.IYE_E) {
            return josa.withoutBatchim
        }

        return if (hasBatchim) josa.withBatchim else josa.withoutBatchim
    }


    /**
     * Categorizes a character into Korean, number, alphabet, or other.
     */
    private fun categoryOf(c: Char) = when (c.lowercaseChar()) {
        in '가'..'힣' -> LetterCategory.KOREAN
        in '0'..'9'   -> LetterCategory.NUMBER
        in 'a'..'z'   -> LetterCategory.ALPHABET
        else          -> LetterCategory.OTHER
    }

    /**
     * Checks whether a given Hangul syllable character has a batchim (final consonant).
     */
    private fun hasBatchimKorean(ch: Char) = (ch.code - '가'.code) % 28 != 0

    /**
     * Heuristic rule to determine whether an English word ends with a batchim-like sound.
     *
     * This is a best-effort phonetic approximation.
     */
    private fun hasBatchimAlphabet(lower: String): Boolean {
        val last = lower.last()
        val prev: Char? = lower.getOrNull(lower.length - 2)

        return when (last) {
            in VOWELS -> false
            'y' -> false
            'e' -> prev != null && prev in PREV_LMN
            'b', 'c' -> prev != null && prev in VOWELS_ALL
            'p' -> prev == null || prev !in PREV_LMN
            'k' -> prev == 'c'
            'g' -> prev == 'n'
            else -> true
        }
    }
}