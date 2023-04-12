package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/*
    Declara os parametros e os retornos da api. O retrofit le a interface e retorna uma lista de string
    Convertendo a req Json em uma lista de string, usando o caminho do @GET e o tipo declarado de retorno
    A partir dessa interface ele gera uma classe concreta para trazer o retorno declarado
 */
interface ChuckNorrisAPI {
    @GET("jokes/categories")    // caminho da url
    fun findAllCategories(@Query("apiKey") apiKey: String = HTTPClient.API_KEY ): Call<List<String>>

    @GET("jokes/random")      // caminho da url
    fun findRandom(@Query("category") categoryName: String? = null,
                   @Query("apiKey") apiKey: String = HTTPClient.API_KEY ) : Call<Joke>
}