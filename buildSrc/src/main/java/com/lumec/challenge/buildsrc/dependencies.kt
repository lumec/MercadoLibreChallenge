package com.lumec.challenge.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.3.1"
    const val googleServices = "com.google.gms:google-services:4.3.14"

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val material = "com.google.android.material:material:1.5.0"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"

        object Activity {
            private const val version = "1.6.1"
            const val compose = "androidx.activity:activity-compose:1.4.0"
        }

        object Lifecycle {
            private const val version = "2.5.1"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.5.3"
            const val compose = "androidx.navigation:navigation-compose:$version"
            const val testing = "androidx.navigation:navigation-testing:$version"
        }

        object Test {

            private const val version = "1.4.0"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.4"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }
            object Espresso {
                private const val version = "3.5.1"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
                const val core = "androidx.test.espresso:espresso-core:$version"
                const val idlingResource = "androidx.test.espresso:espresso-idling-resource:$version"

            }
        }
    }

    object Kotlin {
        private const val version = "1.8.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Arrow {
        private const val version = "1.0.1"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object Coil {
        private const val version = "2.2.2"
        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Compose {
        const val version = "1.3.1"
        const val extVersion = "1.4.3"
        object UI {
            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling =  "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val testJunit4 = "androidx.compose.ui:ui-test-junit4:1.3.3"
            const val testManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }

        object Material {
            const val version = "1.3.1"
            const val material = "androidx.compose.material:material:$version"
        }

        object Foundation {
            const val version = "1.4.0-rc01"
            const val foundation = "androidx.compose.foundation:foundation:$version"
        }

    }

    object Firebase {
        private const val version = "31.1.1"
        const val bom = "com.google.firebase:firebase-bom:$version"

        const val analytics = "com.google.firebase:firebase-analytics"
        const val crashlytics = "com.google.firebase:firebase-crashlytics"

        object Common {
            private const val version = "20.0.0"
            const val ktx = "com.google.firebase:firebase-common-ktx:$version"
        }

        object Crashlytics {
            private const val version = "2.9.2"
            const val gradle = "com.google.firebase:firebase-crashlytics-gradle:$version"
        }
    }

    object Hilt {
        private const val version = "2.44.2"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"
        const val inline = "org.mockito:mockito-inline:4.4.0"
    }

    object OkHttp3 {
        private const val version = "4.9.3"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Timber{
        private const val version = "5.0.1"
        const val timber = "com.jakewharton.timber:timber:$version"
    }
}