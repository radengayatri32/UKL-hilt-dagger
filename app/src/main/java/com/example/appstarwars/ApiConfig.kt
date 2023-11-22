import com.example.appstarwars.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val baseURL = "https://swapi.dev/api/"

    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}
