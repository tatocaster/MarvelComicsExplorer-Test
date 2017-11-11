package me.tatocaster.marvelapp.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import me.tatocaster.marvelapp.BuildConfig
import me.tatocaster.marvelapp.data.api.ApiService
import me.tatocaster.marvelapp.utils.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val ts = System.currentTimeMillis().toString()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/")
                .client(OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .addInterceptor { chain ->
                            val original = chain.request()
                            val originalHttpUrl = original.url()
                            val url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC)
                                    .addQueryParameter("ts", ts)
                                    .addQueryParameter("hash", "".md5(ts + BuildConfig.MARVEL_PRIVATE + BuildConfig.MARVEL_PUBLIC))
                                    .build()
                            val requestBuilder = original.newBuilder().url(url)
                            val request = requestBuilder.build()
                            chain.proceed(request)
                        }
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}