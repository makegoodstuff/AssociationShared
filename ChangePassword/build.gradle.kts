import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform") version "1.3.31"
    `java-library`
}

repositories {
    mavenCentral()
}

val kotlin_version: String by project

val iosFrameworkPrefix: String = "Association"

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
                implementation(kotlin("stdlib-common"))
                implementation("javax.annotation:jsr250-api:1.0")
                implementation("com.android.tools.build:gradle:3.4.0")
                implementation("com.uber.rib:rib-android:0.9.1")
                implementation("com.jakewharton.rxbinding2:rxbinding:2.0.0")
                implementation("com.google.dagger:dagger:2.12")
                implementation("com.google.dagger:dagger-compiler:2.12")
                implementation("com.android.support:percent:25.0.1")
                implementation("com.android.support:appcompat-v7:25.0.1")
                implementation("com.android.support:recyclerview-v7:25.0.1")
                implementation("com.google.android:android:2.2.1")
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
//        val commonTest by getting {
//            dependencies {
//                implementation("junit:junit:4.12")
//                implementation(kotlin("kotlin-test-common:${kotlin_version}"))
//                implementation(kotlin("kotlin-test-junit${kotlin_version}"))
//            }
//        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'io.reactivex.rxjava2:rxjava:2.2.8'
    implementation 'com.uber.rib:rib-android:0.9.1'
    implementation 'com.uber.autodispose:autodispose-kotlin:0.8.0'

    implementation "com.google.dagger:dagger:$daggerVersion"
    implementation "com.google.dagger:dagger-android:$daggerVersion"
    implementation "com.google.dagger:dagger-android-support:$daggerVersion"
    kapt "com.google.dagger:dagger-android-processor:$daggerVersion"
    kapt "com.google.dagger:dagger-compiler:$daggerVersion"
    kaptTest "com.google.dagger:dagger-android-processor:$daggerVersion"
    kaptTest "com.google.dagger:dagger-compiler:$daggerVersion"
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
