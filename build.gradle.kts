buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    // dependencies {
    //     val kotlin_version: String by project
    //     classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlin_version}")
    //     classpath("com.android.tools.build:gradle:3.4.0")
    // }
}

allprojects {
    group = "com.makegoodstuff.association.native"
    version = "0.0.1"

    repositories {
        google()
        maven { setUrl("https://maven.google.com") }

        jcenter()
        mavenCentral()

        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
}