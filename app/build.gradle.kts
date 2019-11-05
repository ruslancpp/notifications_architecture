plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    setCompileSdkVersion(Config.compileSdkVersion)
    defaultConfig {
        applicationId = "com.infiniteset.notifications"
        setMinSdkVersion(Config.minSdkVersion)
        setTargetSdkVersion(Config.targetSdkVersion)
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
    implementation(project(Projects.modulemvp))
    implementation(project(Projects.core))
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidXCoreKtx)
    implementation(Dependencies.jUnit)
}
