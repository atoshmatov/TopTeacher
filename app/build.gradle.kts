plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.gms)
    id("kotlin-parcelize")
}

android {
    namespace = "uz.toshmatov.bookpro"
    compileSdk = 35

    defaultConfig {
        applicationId = "uz.toshmatov.bookpro"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
//    bundle {
//        language {
//            enableSplit = false
//        }
//    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // androidx lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // androidx core-ktx
    implementation(libs.androidx.core.ktx)

    // androidx appcompat
    implementation(libs.androidx.appcompat)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)

    // test
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.junit)

    // debug test
    debugImplementation(libs.bundles.compose.debug)

    // coil
    implementation(libs.bundles.coil)

    // kotlin serialization json
    implementation(libs.kotlinx.serialization.json)

    // timber
    implementation(libs.timber)

    // kotlinx datetime
    implementation(libs.kotlinx.datetime)

    // kotlin serialization json
    implementation(libs.kotlinx.serialization.json)
    api(libs.kotlinx.collections.immutable)

    // kotlin coroutines
    implementation(libs.bundles.coroutines)

    // android dataStore
    implementation(libs.bundles.dataStore)

    // koin compose
    implementation(libs.bundles.koin.compose)

    // lottie animation
    implementation(libs.lottie.compose)

    // ktor client
    implementation(libs.bundles.ktor)

    // voyager
    implementation(libs.bundles.voyager)

    // chucker
    implementation(libs.chucker)

    // splash screen
    implementation(libs.androidx.core.splash)

    // accompanist
    implementation(libs.accompanist.systemui)

    // room database
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // google play service
    implementation(libs.google.play.service)

    // androidx credentials
    implementation(libs.bundles.credentials)

    // google id
    implementation(libs.googleid)

    // lingver
    implementation(libs.lingver)

    // gson
    implementation(libs.gson)
}