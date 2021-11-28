@file:Suppress("LocalVariableName")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0" apply true
}

buildscript {
    val kotlin_version by extra("1.5.21")
    val ktlint_version by extra("0.41.0")
    val hilt_version by extra("2.38.1")

    repositories {
        google()
        mavenCentral()
        // maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    group = "cli"
    delete = setOf(rootProject.buildDir)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    val ktlint_version: String by rootProject.extra

    ktlint {
        version.set(ktlint_version)
        debug.set(false)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        enableExperimentalRules.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
    }
}
