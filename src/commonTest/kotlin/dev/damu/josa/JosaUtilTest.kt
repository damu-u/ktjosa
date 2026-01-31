package dev.damu.josa

import kotlin.test.Test
import kotlin.test.assertEquals

class JosaUtilTest {

    private val testCases = listOf(
        // ================= 이/가 =================
        Triple("사람", Josa.I_GA, "사람이"),
        Triple("친구", Josa.I_GA, "친구가"),
        Triple("123", Josa.I_GA, "123이"),
        Triple("124", Josa.I_GA, "124가"),
        Triple("apple", Josa.I_GA, "apple이"),
        Triple("cat", Josa.I_GA, "cat이"),
        Triple("sky", Josa.I_GA, "sky가"),
        Triple("lamp", Josa.I_GA, "lamp가"),
        Triple("cab", Josa.I_GA, "cab이"),
        Triple("cube", Josa.I_GA, "cube가"),
        Triple("!!!", Josa.I_GA, "!!!가"),
        Triple("   사람   ", Josa.I_GA, "사람이"),

        // ================= 을/를 =================
        Triple("밥", Josa.EUL_REUL, "밥을"),
        Triple("물", Josa.EUL_REUL, "물을"),
        Triple("하늘", Josa.EUL_REUL, "하늘을"),
        Triple("노트", Josa.EUL_REUL, "노트를"),
        Triple("개", Josa.EUL_REUL, "개를"),

        // ================= 은/는 =================
        Triple("책", Josa.EUN_NEUN, "책은"),
        Triple("강아지", Josa.EUN_NEUN, "강아지는"),
        Triple("의자", Josa.EUN_NEUN, "의자는"),
        Triple("코드", Josa.EUN_NEUN, "코드는"),

        // ================= 으로/로 =================
        Triple("바위", Josa.EURO_RO, "바위로"),
        Triple("칼", Josa.EURO_RO, "칼로"),
        Triple("연필", Josa.EURO_RO, "연필로"),
        Triple("망치", Josa.EURO_RO, "망치로"),
        Triple("산", Josa.EURO_RO, "산으로"),

        // ================= 와/과 =================
        Triple("형", Josa.WA_GWA, "형과"),
        Triple("누나", Josa.WA_GWA, "누나와"),
        Triple("아이", Josa.WA_GWA, "아이와"),
        Triple("집", Josa.WA_GWA, "집과"),

        // ================= 이나/나 =================
        Triple("고양이", Josa.INA_NA, "고양이나"),
        Triple("강아지", Josa.INA_NA, "강아지나"),
        Triple("펜", Josa.INA_NA, "펜이나"),

        // ================= 이에/에 =================
        Triple("학교", Josa.IYE_E, "학교에"),
        Triple("도서관", Josa.IYE_E, "도서관에"),
        Triple("약국", Josa.IYE_E, "약국에"),
        Triple("의자", Josa.IYE_E, "의자에"),
        Triple("사과", Josa.IYE_E, "사과에"),
        Triple("아이", Josa.IYE_E, "아이에"),

        // ================= 이란/란 =================
        Triple("사랑", Josa.IRAN_RAN, "사랑이란"),
        Triple("우정", Josa.IRAN_RAN, "우정이란"),
        Triple("의리", Josa.IRAN_RAN, "의리란"),

        // ================= 아/야 =================
        Triple("철수", Josa.A_YA, "철수야"),
        Triple("영희", Josa.A_YA, "영희야"),
        Triple("민정", Josa.A_YA, "민정아"),
        Triple("석현", Josa.A_YA, "석현아"),
        Triple("도윤", Josa.A_YA, "도윤아"),
        Triple("하늘", Josa.A_YA, "하늘아"),
        Triple("수아", Josa.A_YA, "수아야"),
        Triple("하리", Josa.A_YA, "하리야"),
        Triple("이유", Josa.A_YA, "이유야"),

        // ================= 이랑/랑 =================
        Triple("하늘", Josa.IRANG_RANG, "하늘이랑"),
        Triple("땅", Josa.IRANG_RANG, "땅이랑"),
        Triple("별", Josa.IRANG_RANG, "별이랑"),
        Triple("나무", Josa.IRANG_RANG, "나무랑"),

        // ================= 이에요/예요 =================
        Triple("학생", Josa.IYEO_YEO, "학생이에요"),
        Triple("의사", Josa.IYEO_YEO, "의사예요"),
        Triple("가수", Josa.IYEO_YEO, "가수예요"),

        // ================= 으로서/로서 =================
        Triple("대표", Josa.EUROSEO_ROSEO, "대표로서"),
        Triple("학생", Josa.EUROSEO_ROSEO, "학생으로서"),
        Triple("선생님", Josa.EUROSEO_ROSEO, "선생님으로서"),

        // ================= 으로써/로써 =================
        Triple("도구", Josa.EUROSSO_ROSSO, "도구로써"),
        Triple("말", Josa.EUROSSO_ROSSO, "말로써"),
        Triple("사랑", Josa.EUROSSO_ROSSO, "사랑으로써"),

        // ================= 으로부터/로부터 =================
        Triple("부모", Josa.EUROBUTEORO_ROBUTEORO, "부모로부터"),
        Triple("회사", Josa.EUROBUTEORO_ROBUTEORO, "회사로부터"),
        Triple("친구", Josa.EUROBUTEORO_ROBUTEORO, "친구로부터"),
        Triple("선물", Josa.EUROBUTEORO_ROBUTEORO, "선물로부터"),
        Triple("책상", Josa.EUROBUTEORO_ROBUTEORO, "책상으로부터"),
        Triple("도구", Josa.EUROBUTEORO_ROBUTEORO, "도구로부터"),
        Triple("벽", Josa.EUROBUTEORO_ROBUTEORO, "벽으로부터"),
    )

    @Test
    fun testWithJosa() {
        testCases.forEach { (source, josa, expected) ->
            assertEquals(expected, JosaUtil.withJosa(source, josa), "\"$source\" + $josa → expected \"$expected\"")
        }
    }

    @Test
    fun testFindJosaOnly() {
        testCases.forEach { (source, josa, expected) ->
            val resolved = JosaUtil.findJosa(source, josa)
            assertEquals(expected.removePrefix(source.trim()), resolved, "findJosa: \"$source\" + $josa → suffix only")
        }
    }
}
