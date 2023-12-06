@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id(libs.plugins.navigation.safe.args.get().pluginId)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tech.presentation"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(libs.lifecycle.view.model)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.androidx.activitiy)
    implementation(libs.lifecycle.ktx)
    implementation (libs.glide)
    implementation (libs.hilt.core)
    implementation(libs.material)
    implementation(libs.androidx.navigation)
    implementation(libs.fragment)

    kapt( libs.hilt.compiler)
    implementation(libs.androidx.constraint.layout)
    implementation( libs.appcompat)
    implementation( libs.android.core)
    androidTestImplementation (libs.espresso.core)
}