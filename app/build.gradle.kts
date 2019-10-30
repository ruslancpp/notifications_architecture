plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    setCompileSdkVersion(28)
    defaultConfig {
        applicationId = "com.infiniteset.notifications"
        setMinSdkVersion(21)
        setTargetSdkVersion("28")
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidXCore)
    implementation(Dependencies.jUnit)
}
