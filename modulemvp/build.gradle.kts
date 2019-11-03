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
    implementation(project(Projects.core))
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidXCoreKtx)
    implementation(Dependencies.jUnit)
}
