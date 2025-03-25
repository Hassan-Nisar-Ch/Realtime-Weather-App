import java.util.Properties
import java.io.FileInputStream

// read the property
val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}
val WEATHER_API_KEY: String = localProperties.getProperty("WEATHER_API_KEY", "")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.realtimeweatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.realtimeweatherapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Add the API key as a BuildConfig field
        buildConfigField("String", "API_KEY", "\"$WEATHER_API_KEY\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    // gson converter
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")

    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")
}