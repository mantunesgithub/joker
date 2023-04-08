package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieAdapter

class HomeFragment: Fragment() {
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

        val adapter = GroupieAdapter()
        recyclerView.adapter = adapter
        adapter.add(CategoryItem(Category("Categoria 1", 0XFFface6e)))
        adapter.add(CategoryItem(Category("Categoria 2", 0XFFecd16d)))
        adapter.add(CategoryItem(Category("Categoria 3", 0XFFded36e)))
        adapter.add(CategoryItem(Category("Categoria 4", 0XFFcfd571)))
        adapter.notifyDataSetChanged()
    }
}