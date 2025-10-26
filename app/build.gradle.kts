import io.github.klahap.dotenv.DotEnvBuilder

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Hilt Plugin
    alias(libs.plugins.hilt)
    // Kotlin Kapt
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.devvillar.resourl"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.devvillar.resourl"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            val env = DotEnvBuilder.dotEnv {
                addFile("$rootDir/.env.dev")
                addSystemEnv()
            }
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"${env["BASE_URL"]}\"")
            buildConfigField("boolean", "IS_PRODUCTION", "${env["DEBUG_MODE"]}")
        }
        release {
            val env = DotEnvBuilder.dotEnv {
                addFile("$rootDir/.env.prod")
                addSystemEnv()
            }
            isDebuggable = false
            buildConfigField("String", "BASE_URL", "\"${env["BASE_URL"]}\"")
            buildConfigField("boolean", "IS_PRODUCTION", "${env["DEBUG_MODE"]}")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle components
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Coroutines
    implementation(libs.coroutines.android)

}

kapt {
    correctErrorTypes = true
}