import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id ("kotlin-android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.moviez"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        compose = true
    }
    // Load the API key from local.properties
    val properties = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    defaultConfig {
        applicationId = "com.example.moviez"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

       vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "TMDB_API_KEY", "\"${properties["TMDB_API_KEY"]}\"")
            buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
            buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "MID_IMAGE_URL", "\"https://image.tmdb.org/t/p/w300\"")
            buildConfigField("String", "EXTRA_LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w780\"")
            //DEFAULT_WEB_CLIENT_ID
            buildConfigField("String", "DEFAULT_WEB_CLIENT_ID", "\"${properties["DEFAULT_WEB_CLIENT_ID"]}\"")

        }
        debug {
            // Other debug configurations...
            buildConfigField("String", "TMDB_API_KEY", "\"${properties["TMDB_API_KEY"]}\"")
            buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
            buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "MID_IMAGE_URL", "\"https://image.tmdb.org/t/p/w300\"")
            buildConfigField("String", "EXTRA_LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w780\"")
            //DEFAULT_WEB_CLIENT_ID
            buildConfigField("String", "DEFAULT_WEB_CLIENT_ID", "\"${properties["DEFAULT_WEB_CLIENT_ID"]}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "35.0.0"
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

    // Compose
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material3:material3:1.2.0-rc01")
    implementation ("com.google.accompanist:accompanist-pager:0.24.13-rc")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.3")
    // MVVM
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Room
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    // Hilt Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
}

kapt {
    correctErrorTypes = true
}
