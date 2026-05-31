plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

android {
    namespace = "com.dreamrecall.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dreamrecall.app"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // AndroidX & Core
    implementation(libs.core.ktx)
    implementation(libs.splashscreen)
    implementation(libs.activity.compose)

    // Jetpack Compose
    implementation(platform(libs.compose-bom))
    implementation(libs.compose-ui)
    implementation(libs.compose-ui-graphics)
    implementation(libs.compose-ui-tooling-preview)
    implementation(libs.compose-foundation)
    implementation(libs.compose-animation)
    implementation(libs.compose-material3)
    implementation(libs.compose-material.icons.extended)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui.text.google.fonts)

    // Navigation Compose
    implementation(libs.navigation-compose)

    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation-compose)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work.compiler)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // DataStore
    implementation(libs.datastore-preferences)

    // Security & Crypto
    implementation(libs.security-crypto)
    implementation(libs.biometric)

    // WorkManager
    implementation(libs.work-runtime-ktx)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Ktor Client
    implementation(libs.ktor-client-android)
    implementation(libs.ktor-client-content-negotiation)
    implementation(libs.ktor-serialization-kotlinx-json)
    implementation(libs.ktor-client-logging)
    implementation(libs.ktor-client-auth)

    // Coil for Images
    implementation(libs.coil-compose)
    implementation(libs.coil-network-ktor)

    // Vico Charts
    implementation(libs.vico-compose)
    implementation(libs.vico-compose-m3)
    implementation(libs.vico-core)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.room.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.ktor-client-mock)

    // Instrumentation Testing
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso-core)
    androidTestImplementation(platform(libs.compose-bom))
    androidTestImplementation(libs.compose-ui-test-junit4)
    debugImplementation(libs.compose-ui-tooling)
    debugImplementation(libs.compose-ui-test-manifest)
}
