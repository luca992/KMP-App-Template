plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dev.icerock.mobile.multiplatform.resources)
}

kotlin {
    js(IR) {
        browser {
            useCommonJs()
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                scssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        all {
            languageSettings.apply {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(libs.kotlinx.coroutines.core)
            implementation(compose.runtime)
            implementation(compose.runtimeSaveable)
            implementation(libs.moko.resources)
        }

        jsMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(compose.html.core)
                implementation(compose.html.svg)
                implementation(libs.bootstrap.compose)
                implementation(npm("tailwindcss", "3.4.1"))
                implementation(npm("postcss", "8.4.8"))
                implementation(npm("autoprefixer", "10.4.2"))
                implementation(npm("postcss-loader", "4.3.0")) // required to invoke postcss during bundling
            }
        }
    }
}

multiplatformResources {
    resourcesPackage = "com.jetbrains.kmpapp"
}



tasks.register<Copy>("copyTailwindConfig") {
    mustRunAfter(":kotlinNpmInstall")
    val jsFolder = rootProject
        .layout.buildDirectory.asFile.get()
        .resolve("js")
        .resolve("packages")
        .resolve("${rootProject.name}-${project.name}")

    // deleting any existing tailwind.config.js file
    delete(jsFolder.resolve("tailwind.config.js"))
    // Copying tailwind.config.js from root directory to js folder
    from(projectDir.resolve("tailwind.config.js"))
    into(jsFolder)
    delete(jsFolder.resolve("postcss.config.js"))
    // Copying tailwind.config.js from root directory to js folder
    from(projectDir.resolve("postcss.config.js"))
    into(jsFolder)
}

tasks.getByName("compileKotlinJs").dependsOn("copyTailwindConfig")
tasks.getByName("compileKotlinJs").dependsOn("copyTailwindConfig")