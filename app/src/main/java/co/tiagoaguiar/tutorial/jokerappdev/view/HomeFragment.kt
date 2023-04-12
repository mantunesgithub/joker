package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

//1. Ciclo de vida do fragment faz a ação(chamar o PRESENTER para buscar a lista de categorias)
//2. O PRESENTER pede a lista de Categorias no Model
//3. O Model devolve a Lista List<String>
//4. O PRESENTER formata a Lista List<String> em Category (Model)
//5. View pega a lista de List<Category> e converte para o List<CategoryItem>

class HomeFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: HomePresenter
    val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_home_rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        progressBar = view.findViewById(R.id.progress_bar)

        if (adapter.itemCount == 0 ) {
            presenter.findAllCategories()
        }

        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY, categoryName)
            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }
    }

    fun showCategories(response: List<Category>) {
        val categories = response.map{ CategoryItem(it) }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun  showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}