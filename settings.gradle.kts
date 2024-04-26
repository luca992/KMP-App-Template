rootProject.name = "KMP-App-Template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
        plugins {
            // See https://jmfayard.github.io/refreshVersions
            id("de.fayard.refreshVersions") version "0.60.5"
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

include(":shared")
include(":composeApp")
include(":webApp")