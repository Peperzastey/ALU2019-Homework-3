package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import pl.daftacademy.androidlevelup.R
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

class HomeActivity : AppCompatActivity() {
    // movie category strings
    lateinit var ALL_MOVIES: String
    lateinit var ACTION_MOVIES: String
    lateinit var COMEDY_MOVIES: String
    lateinit var CRIME_MOVIES: String
    lateinit var HORROR_MOVIES: String
    lateinit var ROMANCE_MOVIES: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        ALL_MOVIES = getString(R.string.menu_all_movies)
        ACTION_MOVIES = getString(R.string.menu_action_movies)
        COMEDY_MOVIES = getString(R.string.menu_comedy_movies)
        CRIME_MOVIES = getString(R.string.menu_crime_movies)
        HORROR_MOVIES = getString(R.string.menu_horror_movies)
        ROMANCE_MOVIES = getString(R.string.menu_romance_movies)

        nav.setNavigationItemSelectedListener { changeFilter(it) }   // [this] ::changeFilter ?
        if (savedInstanceState == null) filterByCategory("All Movies")
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun changeFilter(item: MenuItem): Boolean {
        //check Kotlin function: TODO("Implement")
        when (item.itemId) {
            R.id.category_all_movies -> filterByCategory(ALL_MOVIES)
            R.id.category_action_movies -> filterByCategory(ACTION_MOVIES)
            R.id.category_comedy_movies -> filterByCategory(COMEDY_MOVIES)
            R.id.category_crime_movies -> filterByCategory(CRIME_MOVIES)
            R.id.category_horror_movies -> filterByCategory(HORROR_MOVIES)
            R.id.category_romance_movies -> filterByCategory(ROMANCE_MOVIES)
            else -> return false
        }
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun filterByCategory(name: String) {
        updateActionBarTitle(name)

        val filter: String? = if (name == ALL_MOVIES) null else name

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MoviesFragment.create(filter))
            .commit()
    }

    private fun updateActionBarTitle(title: CharSequence) {
        supportActionBar?.title = title //check null = title ??
        //TODO check without toolbar in layout
    }
}