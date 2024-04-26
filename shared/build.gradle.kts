import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    jvm()
    js {
        browser()
    }
    @OptIn(ExperimentalWasmDsl::class) wasmJs {
        browser()
    }
    iosX64(); iosArm64(); iosSimulatorArm64()
    macosArm64(); macosX64()

    applyDefaultHierarchyTemplate()
    sourceSets {
        appleMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.swing)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.kotlinx.coroutines.core)
            api(libs.koin.core)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
    }
}
