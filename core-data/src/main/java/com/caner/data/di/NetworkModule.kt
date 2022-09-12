package com.caner.data.di

import com.caner.common.utils.HttpParams
import com.caner.common.utils.HttpRoutes
import com.caner.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideHttpClient() = HttpClient(OkHttp) {
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