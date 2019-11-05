private const val androidGradlePluginVersion = "3.5.2"
private const val kotlinVersion = "1.3.50"
private const val appCompatVersion = "1.1.0"
private const val androidXCoreVersion = "1.1.0"
private const val mosbyMvpVersion = "3.1.1"
private const val jUnitVersion = "4.12"

object Plugins {
    const val androidGradle = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val androidXCoreKtx = "androidx.core:core-ktx:$androidXCoreVersion"
    const val mosbyMvp = "com.hannesdorfmann.mosby3:mvp:$mosbyMvpVersion"
    const val jUnit = "junit:junit:$jUnitVersion"
}

object Config {
    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = "28"
}

object Projects {
    const val modulemvp = ":modulemvp"
    const val core = ":core"
}