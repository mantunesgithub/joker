package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeFragment: Fragment() {

    companion object{
        const val CATEGORY_KEY = "category"
    }
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var presenter: JokePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = JokePresenter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke   , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)  // !! poderia colocar !! Somente quando tem certeza que não é null

        categoryName?.let {
            activity?.findViewById<Toolbar>(R.id.app_bar_main_toolbar)?.title = categoryName
            progressBar = view.findViewById(R.id.fragment_joke_progress_bar)
            textView = view.findViewById(R.id.fragment_joke_joke_txt)
            imageView = view.findViewById(R.id.fragment_joke_joke_img)

            view.findViewById<FloatingActionButton>(R.id.fragment_joke_fab).setOnClickListener{
                presenter.findByCategoryName(categoryName)
            }
            presenter.findByCategoryName(categoryName)
        }
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        Picasso.get().load(joke.iconUrl).into(imageView)        //faz uma thread paralela, faz a Req, faz download
                                                                //da foto e coloca na imageview. Qdo a Url é a mesma
                                                                // ele pega da memoria que ele guardou, não demora
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