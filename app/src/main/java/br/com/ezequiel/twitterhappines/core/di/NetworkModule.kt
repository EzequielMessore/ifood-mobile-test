package br.com.ezequiel.twitterhappines.core.di

import br.com.ezequiel.twitterhappines.BuildConfig
import br.com.ezequiel.twitterhappines.data.ws.language.LanguageApi
import br.com.ezequiel.twitterhappines.data.ws.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule @Inject constructor() {
    @Provides
    @Reusable
    @Named("twitter")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        okHttpClientBuilder
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptorLogging(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesUserApi(@Named("twitter") retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Reusable
    @Named("language")
    fun provideLanguageRetrofit(@Named("language") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://language.googleapis.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("language")
    fun providesLanguageOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        okHttpClientBuilder
            .addInterceptor(httpLoggingInterceptor)
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun providesLanguageApi(@Named("language") retrofit: Retrofit): LanguageApi =
        retrofit.create(LanguageApi::class.java)
}
