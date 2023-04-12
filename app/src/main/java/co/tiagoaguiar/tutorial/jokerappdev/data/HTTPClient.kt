package co.tiagoaguiar.tutorial.jokerappdev.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*  1. Cria object para fazer as declarações e requisições. O object fica vivo durante toda a vida do app
    2. Cria uma interface ChuckNorrisAPI.kt. O retrofit le a interface e retorna uma lista de string
       Convertendo a req Json em uma lista de string, usando o camonho do @GET
    3. Criar a classe CategoryRemoteDataSource para o retrofit fazer a req no servidor baseado
       nas declaraçoes da interface
 */
object HTTPClient {
    private const val BASE_URl = "https://api.tiagoaguiar.co/jokerapp/"
    const val API_KEY = "d0f4c89f-0f00-47c5-9182-6bfb836cc3bf"

    private fun httpclient() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY   //Para verificar a log qdo retorna erro retrofit
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    // Na log filtrar por OKHttp vai aparecer a log

    fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpclient())               //fun httpclient() para interceptar as chamadas para log
        .build()                            //esse aqui é o que cria o retrofit
 }