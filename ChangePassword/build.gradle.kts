import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    `java-library`
    kotlin("multiplatform") version "1.3.31"
    kotlin("kapt") version "1.3.31"
    kotlin("android.extensions") version "1.3.31"
}

repositories {
    google()
    mavenCentral()
}

val kotlin_version: String by project

val iosFrameworkPrefix: String = "LoggedOut"

kotlin {
    jvm("android")

    val ios: KotlinNativeTarget
    if (false) {  // TODO: actually select a target depending on environment
        ios = iosArm64("ios")
    } else {
        ios = iosX64("ios")
    }

    println("Set up targets: ${targets.names} in ${project.name}")

    ios.binaries {
        val buildTypes = listOf<NativeBuildType>(DEBUG)
        framework(iosFrameworkPrefix, buildTypes)
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                kapt {
                    annotationProcessor("com.google.dagger:dagger-compiler:2.12")
                    annotationProcessor("com.uber.rib:rib-compiler-test:0.9.1")
                    annotationProcessor("com.uber.nullaway:nullaway:0.3.2")
                }

                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlin.android:1.3.31")

                implementation("com.google.android:android:2.2.1")
                implementation("com.android.tools.build:gradle:3.4.0")
                implementation("com.android.support.constraint:constraint-layout:1.1.3")
                implementation("com.android.support:appcompat-v7:28.0.0")

                implementation("com.uber.rib:rib-android:0.9.1")

                implementation("com.google.dagger:dagger:2.12")

                implementation("javax.annotation:jsr250-api:1.0")


                implementation("com.jakewharton.rxbinding2:rxbinding:2.0.0")

                implementation("io.reactivex.rxjava2:rxandroid:2.0.1")
                implementation("io.reactivex.rxjava2:rxjava:2.1.3")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }

        val commonTest by getting {
            dependencies {
                implementation("junit:junit:4.12")
                implementation(kotlin("kotlin-test-common:${kotlin_version}"))
                implementation(kotlin("kotlin-test-junit${kotlin_version}"))
            }
        }
    }
}

tasks {
    val build by existing
    val export by registering(Copy::class) {
        println("Exporting iOS Framework")
        val ios = kotlin.targets["ios"] as KotlinNativeTarget?
        if (ios != null) {
            val fmwk: Framework = ios.binaries.getFramework(iosFrameworkPrefix, NativeBuildType.DEBUG)

            from(fmwk.outputFile)
            val targetDirectory: String? by rootProject.extra

            if (targetDirectory != null) {
                println("Copying iOS Framework to $targetDirectory")
                into(targetDirectory as String)
            } else {
                println("iOS Framework exported to ${fmwk.outputFile}")
                into(fmwk.outputDirectory)
            }
        }

        dependsOn(build)
    }
}
