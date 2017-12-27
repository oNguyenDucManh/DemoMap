package manhnd.com.mapdemo

import android.content.Context
import com.google.gson.GsonBuilder
import com.vn.ezlearn.network.RxErrorHandlingCallAdapterFactory
import manhnd.com.mapdemo.directions.Directionresult
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by FRAMGIA\nguyen.duc.manh on 16/02/2017.
 */

interface ApiService {

    @GET("https://maps.googleapis.com/maps/api/directions/json")
    fun direction(@Query("origin") origin: String, @Query("destination") destination: String,
                  @Query("key") key: String): Observable<Directionresult>

    object Factory {

        fun create(context: Context): ApiService {
            // Create Retrofit Adapter
            val gson = GsonBuilder().serializeNulls().create()
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/")
                    .client(getOkHttp(context))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        private fun getOkHttp(context: Context): OkHttpClient {

            // Config Log
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // Create Http Client
            val httpClient = OkHttpClient.Builder()

            with(httpClient) {
                addInterceptor { chain ->
                    val builder = chain.request().newBuilder()
                    chain.proceed(builder.build())
                            .newBuilder()
                            .build()
                }
                connectTimeout(10, TimeUnit.SECONDS)
                readTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    addInterceptor(logging)
                }
                // Config Http Cache
                val cacheSize = 10 * 1024 * 1024 // 10 MiB
                val cache = Cache(context.cacheDir, cacheSize.toLong())
                cache(cache)
            }
            return httpClient.build()
        }
    }
}
