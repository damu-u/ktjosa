import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.0"
    id("com.vanniktech.maven.publish") version "0.34.0"
}

group = "dev.damu.ktjosa"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

mavenPublishing {
    coordinates(group.toString(), project.name, version.toString())

    pom {
        name.set("ktjosa")
        description.set("Korean josa particle appender for Kotlin.")
        url.set("https://github.com/damu-u/ktjosa")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("damu")
                name.set("BeomSeok Choi")
                email.set("zmmx019@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/damu-u/ktjosa")
            connection.set("scm:git:git://github.com/damu-u/ktjosa.git")
            developerConnection.set("scm:git:ssh://git@github.com/damu-u/ktjosa.git")
        }
    }

    publishToMavenCentral()
    signAllPublications()
}