# ktjosa

한국어 조사 자동 처리 유틸리티 라이브러리

---

## ✨ Features

- `이/가`, `을/를`, `은/는` 등 조사 자동 판별
- 숫자, 알파벳, 특수문자, 한글 모두 지원
- 조사만 추출하거나 단어+조사 합성 둘 다 가능
- 종성 'ㄹ' 예외 (예: "칼로", "말로써") 처리 포함

---

## 📦 Installation

Gradle:

```kotlin
dependencies {
    implementation("dev.damu.ktjosa:ktjosa:1.0.0")
}
```

---

## ⚙️ Usage

```kotlin
val result1 = JosaUtil.withJosa("사람", Josa.I_GA)
// 사람 + 이/가 → "사람이"

val result2 = JosaUtil.withJosa("고양이", Josa.EUN_NEUN)
// 고양이 + 은/는 → "고양이는"

val josaOnly = JosaUtil.findJosa("책", Josa.EUL_REUL)
// "책" + 을/를 → 조사만 추출 → "을"
```

---

## 🔤 Supported Josa

| Enum | Output |
|------|--------|
| `I_GA` | 이 / 가 |
| `EUL_REUL` | 을 / 를 |
| `EUN_NEUN` | 은 / 는 |
| `EURO_RO` | 으로 / 로 (종성 ㄹ 예외 포함) |
| `WA_GWA` | 과 / 와 |
| `INA_NA` | 이나 / 나 |
| `IYE_E` | 이에 / 에 (항상 에) |
| `IRAN_RAN` | 이란 / 란 |
| `A_YA` | 아 / 야 |
| `IRANG_RANG` | 이랑 / 랑 |
| `IYEO_YEO` | 이에요 / 예요 |
| `EUROSEO_ROSEO` | 으로서 / 로서 |
| `EUROSSO_ROSSO` | 으로써 / 로써 |
| `EUROBUTEORO_ROBUTEORO` | 으로부터 / 로부터 |

---

## 📜 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).