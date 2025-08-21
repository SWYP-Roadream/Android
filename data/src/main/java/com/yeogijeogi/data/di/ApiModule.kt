package com.yeogijeogi.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yeogijeogi.data.BuildConfig
import com.yeogijeogi.data.database.DataStoreSessionStorage
import com.yeogijeogi.data.model.AuthInfoEntity
import com.yeogijeogi.data.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val DATA_STORE_NAME = "roadream"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun provideSessionStorage(dataStore: DataStore<Preferences>): DataStoreSessionStorage {
        return DataStoreSessionStorage(dataStore)
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideOkHttp(dataStore: DataStoreSessionStorage): OkHttpClient {
        val interceptor =
            Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()

                val response = chain.proceed(request)
                response.headers.forEach { (key, value) ->
                    Timber.e("headers key $key, value $value")
                }
                val token = response.headers["Authorization"]?.split(" ", limit = 2)?.last()
                val refreshToken = response.headers["X-Refresh-Token"]
                Timber.e("token : $token, refreshToken $refreshToken")
                if (token != null && refreshToken != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.setToken(AuthInfoEntity(token, refreshToken))
                    }
                }
                response
            }

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginRetrofit(okHttpClient: OkHttpClient, converterFactory: Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_KEY)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}