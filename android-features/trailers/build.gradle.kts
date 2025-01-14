plugins {
    id("tvmaniac.android.feature")
}

android {
    namespace = "com.thomaskioko.tvmaniac.videoplayer"
}

dependencies {
    api(projects.presentation.trailers)

    implementation(libs.accompanist.insetsui)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.flowredux)
    implementation(libs.youtubePlayer)
}