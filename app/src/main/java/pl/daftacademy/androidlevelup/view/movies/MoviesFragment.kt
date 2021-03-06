package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
        val categoryFilter = arguments?.getString(CATEGORY_EXTRA)
        if (categoryFilter == null)
            adapter.items = viewModel.getMovies()
        else
            adapter.items = viewModel.getMoviesByCategory(categoryFilter)
    }

    companion object {
        const val CATEGORY_EXTRA = "CATEGORY_EXTRA"

        fun create(categoryFilter: String? = null): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString(CATEGORY_EXTRA, categoryFilter)
            // null - the same as no mapping at all
            fragment.arguments = args
            return fragment
        }
    }
}
