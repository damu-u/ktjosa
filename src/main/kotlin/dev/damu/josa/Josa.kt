package dev.damu.josa

/**
 * Enum class representing Korean postpositions (josa),
 * which change form depending on whether the preceding word has a batchim (final consonant).
 *
 * Each josa has two forms:
 * - [withBatchim]: used when the preceding word ends with a consonant
 * - [withoutBatchim]: used when it ends with a vowel
 *
 * @property withBatchim the form used after a final consonant (받침 있음)
 * @property withoutBatchim the form used after a vowel (받침 없음)
 * @property rieulEndsWithRo whether this josa follows the special rule for final 'ㄹ' in Korean
 */
enum class Josa(
    val withBatchim: String,
    val withoutBatchim: String,
    val rieulEndsWithRo: Boolean = false
) {
    /** 이/가 */
    I_GA("이", "가"),

    /** 을/를 */
    EUL_REUL("을", "를"),

    /** 은/는 */
    EUN_NEUN("은", "는"),

    /** 으로/로 - 'ㄹ' 종성 예외 적용 */
    EURO_RO("으로", "로", rieulEndsWithRo = true),

    /** 과/와 */
    WA_GWA("과", "와"),

    /** 이나/나 */
    INA_NA("이나", "나"),

    /** 이에/에 - 예외: 항상 '에' 사용 */
    IYE_E("이에", "에"),

    /** 이란/란 */
    IRAN_RAN("이란", "란"),

    /** 아/야 */
    A_YA("아", "야"),

    /** 이랑/랑 */
    IRANG_RANG("이랑", "랑"),

    /** 이에요/예요 */
    IYEO_YEO("이에요", "예요"),

    /** 으로서/로서 - 'ㄹ' 종성 예외 적용 */
    EUROSEO_ROSEO("으로서", "로서", rieulEndsWithRo = true),

    /** 으로써/로써 - 'ㄹ' 종성 예외 적용 */
    EUROSSO_ROSSO("으로써", "로써", rieulEndsWithRo = true),

    /** 으로부터/로부터 - 'ㄹ' 종성 예외 적용 */
    EUROBUTEORO_ROBUTEORO("으로부터", "로부터", rieulEndsWithRo = true);
}