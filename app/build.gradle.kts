import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.alonso.challengecompose"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.alonso.challengecompose"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
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

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":core:ui"))

    //Module Dashboard
    implementation(project(":feature:dashboard:presentation"))

    //Module Home
    implementation(project(":feature:home:presentation")) // <-
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))

    //Module Search
    implementation(project(":feature:search:presentation"))//<-
    implementation(project(":feature:search:domain"))
    implementation(project(":feature:search:data"))

    //Module Detail
    implementation(project(":feature:detail:presentation"))

    //Module Splash
    implementation(project(":feature:splash:presentation"))

    //Module Favorite
    implementation(project(":feature:favorite:presentation"))
    implementation(project(":feature:favorite:domain"))
    implementation(project(":feature:favorite:data"))

    //Module Favorite
    implementation(project(":feature:setting:presentation"))
    //Others
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))

    // Hilt
    implementation(libs.compose.hilt.navigation)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //splashscreen
    implementation(libs.core.splashscreen)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}