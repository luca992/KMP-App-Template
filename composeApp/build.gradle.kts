import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    jvm("desktopJvm")
    js {
        browser()
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class) wasmJs {
        browser()
        binaries.executable()
    }
    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    listOf(
        macosArm64(), macosX64()
    ).forEach { macOsTarget ->
        macOsTarget.binaries.executable()
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
            }
        }
        val desktopJvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kamel)
        }
    }
}

android {
    namespace = "com.jetbrains.kmpapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.jetbrains.kmpapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose {
    experimental {
        web.application { }
    }
    desktop {
        application {
            mainClass = "MainKt"
        }
    }
}
