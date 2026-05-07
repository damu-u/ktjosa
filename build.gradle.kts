import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.3.0"
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "dev.damu.ktjosa"
version = "1.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    iosArm64()
    iosSimulatorArm64()

    js {
        browser()
        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }
    }
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
                name.set("Damu")
                email.set("damu@damu.dev")
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
