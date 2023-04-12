package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    fun  findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 0    //H - matiz = 40   faixa de 
        val end = 320   //H - matiz = 190   cores
        val diff = (end-start) / response.size
        val categories = response.mapIndexed() { index, value ->  //index=corrente value=valor em si
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),   // 40 + (9,30 * 0) // 40 + (9,30 * 1) ...
                100.0f,
                100.0f
            )
            Category(value, Color.HSVToColor(hsv).toLong())
        }
        view.showCategories(categories)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }
}
/* Opções  para val categories = response.map { Category(it, 0xffff0000) }
        val categories = mutableListOf<CategoryItem>()
        for (category in response) {
            categories.add(CategoryItem(category))
        }
    }  Outra opção
        val categories = response.map { category ->
            CategoryItem(category)
        }
*/
/* override fun onSuccess(response: List<String>) {
    Para fazer a lista de cores, entrar no site colordesigner.io
    Eixo H=matiz, a cor em si, diferença de tonalidades de inicio e fim da cor desejada
    Eixo S=saturação, de 0 ate 100% do mais claro ate mais escuro na horizontal
    Eixo V=valor, da cor que quer trabalhar, de 0 ate 100% do mais escuro ate mais preto na vertical
    Entao S=100% V=100% H=algorítmo para calcular de um ponto ao outro Ex.: Azul=40 Laranja+190
    Diferença = 190 - 40 = 150 possilidades. A lista tem 16 elementos, 150/16= 9,3 passos
    Temos que interar de 9,3 em 9,3 16 vezes
 */
