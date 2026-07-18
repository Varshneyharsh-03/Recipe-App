plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.recipeapp"
    compileSdk  = 37

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    val ktor_version = "3.5.1"
    implementation("io.ktor:ktor-client-core:${ktor_version}")
    implementation("io.ktor:ktor-client-okhttp:${ktor_version}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktor_version}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktor_version}")
    implementation("io.ktor:ktor-client-logging:${ktor_version}")
    implementation("io.ktor:ktor-client-android:${ktor_version}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")

    implementation("androidx.navigation:navigation-compose:2.8.8")

    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation("androidx.compose.material:material-icons-extended:1.7.1")


}