plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    setCompileSdkVersion(Config.compileSdkVersion)
    defaultConfig {
        setMinSdkVersion(Config.minSdkVersion)
        setTargetSdkVersion(Config.targetSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidXCore)
    implementation(Dependencies.jUnit)
}
