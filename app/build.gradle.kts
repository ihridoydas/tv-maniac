import com.thomaskioko.tvmaniac.extensions.TvManiacBuildType

plugins {
    id("tvmaniac.application")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.thomaskioko.tvmaniac"

    defaultConfig {
        applicationId = "com.thomaskioko.tvmaniac"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = TvManiacBuildType.DEBUG.applicationIdSuffix
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(projects.androidCore.designsystem)
    implementation(projects.androidCore.navigation)
    implementation(projects.androidCore.traktAuth)
    implementation(projects.androidCore.workmanager)
    implementation(projects.androidFeatures.discover)
    implementation(projects.androidFeatures.home)
    implementation(projects.androidFeatures.search)
    implementation(projects.androidFeatures.showDetails)
    implementation(projects.androidFeatures.showsGrid)
    implementation(projects.androidFeatures.following)
    implementation(projects.androidFeatures.settings)
    implementation(projects.androidFeatures.seasonDetails)
    implementation(projects.androidFeatures.trailers)
    implementation(projects.androidFeatures.profile)

    implementation(projects.core.database)
    implementation(projects.core.networkutil)
    implementation(projects.core.util)
    implementation(projects.core.datastore.api)
    implementation(projects.core.datastore.implementation)
    implementation(projects.core.tmdbApi.api)
    implementation(projects.core.tmdbApi.implementation)
    implementation(projects.core.traktApi.api)
    implementation(projects.core.traktApi.implementation)

    implementation(projects.data.category.api)
    implementation(projects.data.category.implementation)
    implementation(projects.data.episodes.api)
    implementation(projects.data.episodes.implementation)
    implementation(projects.data.profile.api)
    implementation(projects.data.profile.implementation)
    implementation(projects.data.similar.api)
    implementation(projects.data.similar.implementation)
    implementation(projects.data.seasonDetails.api)
    implementation(projects.data.seasonDetails.implementation)
    implementation(projects.data.shows.api)
    implementation(projects.data.shows.implementation)
    implementation(projects.data.trailers.api)
    implementation(projects.data.trailers.implementation)

    implementation(projects.domain.discover)
    implementation(projects.domain.following)
    implementation(projects.domain.seasondetails)
    implementation(projects.domain.settings)
    implementation(projects.domain.showDetails)
    implementation(projects.domain.trailers)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.compose.activity)
    implementation(libs.appauth)

    implementation(libs.kotlinInject.runtime)
    ksp(libs.kotlinInject.compiler)
}