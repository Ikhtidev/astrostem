plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "uz.sayfullayeva.astrostem"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.sayfullayeva.astrostem"
        minSdk = 21
        targetSdk = 34
        versionCode = 3
        versionName = "1.1"

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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // lottie animations
    implementation("com.airbnb.android:lottie:4.2.1")

    // pdfViewer
    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")

    //room
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    // jsoup for html render
//    implementation("org.jsoup:jsoup:1.13.1")

    // for read .xls file
    implementation("org.apache.poi:poi:3.17")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    //for exoplayer
//    val mediaVersion = "1.0.1"
//    implementation("androidx.media3:media3-exoplayer:$mediaVersion")
//    implementation("androidx.media3:media3-ui:$mediaVersion")
//    implementation("androidx.media3:media3-exoplayer-dash:$mediaVersion")
//    implementation("androidx.media3:media3-exoplayer-rtsp:1.1.0")

    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")


}