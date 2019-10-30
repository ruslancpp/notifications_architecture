// Top-level build file where you can add configuration options common to all sub-projects/modules.
import Plugins

buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Plugins.androidGradle)
        classpath(Plugins.kotlinGradle)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
