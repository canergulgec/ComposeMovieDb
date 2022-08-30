package com.caner.composemoviedb.di

import android.content.Context
import android.util.Log
import com.caner.composemoviedb.BuildConfig
import com.caner.composemoviedb.utils.network.HttpParams
import com.caner.composemoviedb.utils.network.HttpRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context) = HttpClient(OkHttp) {
        defaultRequest {
            host = HttpRoutes.BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
            parameter(HttpParams.API_KEY, BuildConfig.API_KEY)
        }

        Logging {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.v("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }

        ResponseObserver { response ->
            Timber.d("HTTP status:", "${response.status.value}")
        }

        install(JsonFeature) {
            serializer = GsonSerializer {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }

        install(HttpTimeout) {
            connectTimeoutMillis = HttpParams.TIME_OUT
            socketTimeoutMillis = HttpParams.TIME_OUT
        }
    }
}