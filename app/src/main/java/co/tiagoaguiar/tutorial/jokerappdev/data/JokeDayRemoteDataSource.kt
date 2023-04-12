package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/* Criar a classe JokeRemoteDataSource para o retrofit fazer a req no servidor baseado
    nas declaraçoes da interface
*/
class JokeDayRemoteDataSource {
    fun findRandom(callback: JokeCallback) {
        HTTPClient.retrofit()                       //aqui tem a instancia do retrofit
            .create(ChuckNorrisAPI::class.java)     //classe da interface com a declaração para o retrofit fazer
                                                    // a req no servidor baseado nas declaraçoes da interface
            .findRandom()       //fun da interface
            .enqueue(object: Callback<Joke> {       //enfileirar e fazer que a execução seja assincrona
                                                    // vai devolver um Callback com mesmo tipo de ret da interface e response com ok ou erro

                override fun onResponse(call: Call<Joke>, response: Response<Joke>){
                    if (response.isSuccessful) {
                        val joke = response.body()    //No response tem a Lista de categorias
                        callback.onSuccess(joke ?: throw RuntimeException("Joke not found")      )
                    } else {
                        //quando p servidor devolve erro status < 500.Não é erro do servidor, São erros da chamada do app
                        val error = response.errorBody()?.toString()
                        callback.onError(error ?:"Erro desconhecido" )
                    }
                    callback.onComplete()
                }
                       //Excessao
                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno ")  //devolve para o PRESENTER a msg
                    callback.onComplete()
                }

            })
    }
}





//      Simular uma requisção HTTP
////Processo Assincrono para não travar interface grafica
//Handler(Looper.getMainLooper()).postDelayed({
//    val response = arrayListOf(
//        "Categoria 1",
//        "Categoria 2",
//        "Categoria 3",
//        "Categoria 4"
//    )
//    Log.i("TAG", "findAllCategories: {CategoryRemoteDataSource} $response")
//    callback.onSuccess(response)
//    //callback.onError("Falha na conexão com Servidor. Tente mais tarde")
//    callback.onComplete()
//}, 4000)
