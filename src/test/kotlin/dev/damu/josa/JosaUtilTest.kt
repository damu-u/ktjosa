package dev.damu.josa

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class JosaUtilTest {

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> = Stream.of(
            // ================= 이/가 =================
            Arguments.of("사람", Josa.I_GA, "사람이"),
            Arguments.of("친구", Josa.I_GA, "친구가"),
            Arguments.of("123", Josa.I_GA, "123이"),
            Arguments.of("124", Josa.I_GA, "124가"),
            Arguments.of("apple", Josa.I_GA, "apple이"),
            Arguments.of("cat", Josa.I_GA, "cat이"),
            Arguments.of("sky", Josa.I_GA, "sky가"),
            Arguments.of("lamp", Josa.I_GA, "lamp가"),
            Arguments.of("cab", Josa.I_GA, "cab이"),
            Arguments.of("cube", Josa.I_GA, "cube가"),
            Arguments.of("!!!", Josa.I_GA, "!!!가"),
            Arguments.of("   사람   ", Josa.I_GA, "사람이"),

            // ================= 을/를 =================
            Arguments.of("밥", Josa.EUL_REUL, "밥을"),
            Arguments.of("물", Josa.EUL_REUL, "물을"),
            Arguments.of("하늘", Josa.EUL_REUL, "하늘을"),
            Arguments.of("노트", Josa.EUL_REUL, "노트를"),
            Arguments.of("개", Josa.EUL_REUL, "개를"),

            // ================= 은/는 =================
            Arguments.of("책", Josa.EUN_NEUN, "책은"),
            Arguments.of("강아지", Josa.EUN_NEUN, "강아지는"),
            Arguments.of("의자", Josa.EUN_NEUN, "의자는"),
            Arguments.of("코드", Josa.EUN_NEUN, "코드는"),

            // ================= 으로/로 =================
            Arguments.of("바위", Josa.EURO_RO, "바위로"),
            Arguments.of("칼", Josa.EURO_RO, "칼로"),
            Arguments.of("연필", Josa.EURO_RO, "연필로"),
            Arguments.of("망치", Josa.EURO_RO, "망치로"),
            Arguments.of("산", Josa.EURO_RO, "산으로"),

            // ================= 와/과 =================
            Arguments.of("형", Josa.WA_GWA, "형과"),
            Arguments.of("누나", Josa.WA_GWA, "누나와"),
            Arguments.of("아이", Josa.WA_GWA, "아이와"),
            Arguments.of("집", Josa.WA_GWA, "집과"),

            // ================= 이나/나 =================
            Arguments.of("고양이", Josa.INA_NA, "고양이나"),
            Arguments.of("강아지", Josa.INA_NA, "강아지나"),
            Arguments.of("펜", Josa.INA_NA, "펜이나"),

            // ================= 이에/에 =================
            Arguments.of("학교", Josa.IYE_E, "학교에"),
            Arguments.of("도서관", Josa.IYE_E, "도서관에"),
            Arguments.of("약국", Josa.IYE_E, "약국에"),
            Arguments.of("의자", Josa.IYE_E, "의자에"),
            Arguments.of("사과", Josa.IYE_E, "사과에"),
            Arguments.of("아이", Josa.IYE_E, "아이에"),

            // ================= 이란/란 =================
            Arguments.of("사랑", Josa.IRAN_RAN, "사랑이란"),
            Arguments.of("우정", Josa.IRAN_RAN, "우정이란"),
            Arguments.of("의리", Josa.IRAN_RAN, "의리란"),

            // ================= 아/야 =================
            Arguments.of("철수", Josa.A_YA, "철수야"),
            Arguments.of("영희", Josa.A_YA, "영희야"),
            Arguments.of("민정", Josa.A_YA, "민정아"),
            Arguments.of("석현", Josa.A_YA, "석현아"),
            Arguments.of("도윤", Josa.A_YA, "도윤아"),
            Arguments.of("하늘", Josa.A_YA, "하늘아"),
            Arguments.of("수아", Josa.A_YA, "수아야"),
            Arguments.of("하리", Josa.A_YA, "하리야"),
            Arguments.of("이유", Josa.A_YA, "이유야"),

            // ================= 이랑/랑 =================
            Arguments.of("하늘", Josa.IRANG_RANG, "하늘이랑"),
            Arguments.of("땅", Josa.IRANG_RANG, "땅이랑"),
            Arguments.of("별", Josa.IRANG_RANG, "별이랑"),
            Arguments.of("나무", Josa.IRANG_RANG, "나무랑"),

            // ================= 이에요/예요 =================
            Arguments.of("학생", Josa.IYEO_YEO, "학생이에요"),
            Arguments.of("의사", Josa.IYEO_YEO, "의사예요"),
            Arguments.of("가수", Josa.IYEO_YEO, "가수예요"),

            // ================= 으로서/로서 =================
            Arguments.of("대표", Josa.EUROSEO_ROSEO, "대표로서"),
            Arguments.of("학생", Josa.EUROSEO_ROSEO, "학생으로서"),
            Arguments.of("선생님", Josa.EUROSEO_ROSEO, "선생님으로서"),

            // ================= 으로써/로써 =================
            Arguments.of("도구", Josa.EUROSSO_ROSSO, "도구로써"),
            Arguments.of("말", Josa.EUROSSO_ROSSO, "말로써"),
            Arguments.of("사랑", Josa.EUROSSO_ROSSO, "사랑으로써"),

            // ================= 으로부터/로부터 =================
            Arguments.of("부모", Josa.EUROBUTEORO_ROBUTEORO, "부모로부터"),
            Arguments.of("회사", Josa.EUROBUTEORO_ROBUTEORO, "회사로부터"),
            Arguments.of("친구", Josa.EUROBUTEORO_ROBUTEORO, "친구로부터"),
            Arguments.of("선물", Josa.EUROBUTEORO_ROBUTEORO, "선물로부터"),
            Arguments.of("책상", Josa.EUROBUTEORO_ROBUTEORO, "책상으로부터"),
            Arguments.of("도구", Josa.EUROBUTEORO_ROBUTEORO, "도구로부터"),
            Arguments.of("벽", Josa.EUROBUTEORO_ROBUTEORO, "벽으로부터"),
        )
    }

    @ParameterizedTest(name = "\"{0}\" + {1} → \"{2}\"")
    @MethodSource("data")
    fun testWithJosa(source: String, josa: Josa, expected: String) {
        assertEquals(expected, JosaUtil.withJosa(source, josa))
    }

    @ParameterizedTest(name = "findJosa: \"{0}\" + {1} → \"{2}\" (suffix only)")
    @MethodSource("data")
    fun testFindJosaOnly(source: String, josa: Josa, expected: String) {
        val resolved = JosaUtil.findJosa(source, josa)
        assertEquals(expected.removePrefix(source.trim()), resolved)
    }
}