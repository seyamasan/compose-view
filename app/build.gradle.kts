plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
//    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.screenshot)
}

android {
    namespace = "com.example.composeview"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.composeview"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests { isIncludeAndroidResources = true }
    }

    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // テストルール
    androidTestImplementation(libs.androidx.ui.test.junit4)
    implementation(libs.androidx.ui.test.junit4.android) // createComposeRule()使うにはこれが必要だった

    // icons extended
    implementation(libs.compose.material.icons.extended)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // hilt
//    implementation(libs.hilt.android)
//    implementation(libs.androidx.hilt.navigation.compose)
//    ksp(libs.hilt.compiler)

    // serialization
    implementation(libs.kotlinx.serialization.core)

    // lifecycle-viewmodel-compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // テスト用アサーションライブラリ
    testImplementation(libs.truth)

    // Robolectric 実機やエミュレータを使わずにAndroidテストを実行できるテストフレームワーク
    testImplementation(libs.robolectric)

    // スクリーンショットテスト
    screenshotTestImplementation(libs.androidx.ui.tooling)

}