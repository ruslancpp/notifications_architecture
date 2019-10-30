private const val androidGradlePluginVersion = "3.5.1"
private const val kotlinVersion = "1.3.50"
private const val appCompatVersion = "1.1.0"
private const val androidXCoreVersion = "1.1.0"
private const val jUnitVersion = "4.12"

object Plugins {
    const val androidGradle = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val androidXCore = "androidx.core:core-ktx:$androidXCoreVersion"
    const val jUnit = "junit:junit:$jUnitVersion"
}
