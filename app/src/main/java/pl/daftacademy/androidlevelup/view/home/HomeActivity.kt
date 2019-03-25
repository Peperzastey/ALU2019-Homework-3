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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav.setNavigationItemSelectedListener { changeFilter(it) }   // [this] ::changeFilter ?
        if (savedInstanceState == null) filterByCategory(null)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
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
            R.id.category_all_movies -> filterByCategory(null)
            R.id.category_action_movies -> filterByCategory("Action")
            R.id.category_comedy_movies -> filterByCategory("Comedy")
            R.id.category_crime_movies -> filterByCategory("Crime")
            R.id.category_horror_movies -> filterByCategory("Horror")
            R.id.category_romance_movies -> filterByCategory("Romance")
            else -> return false
        }
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun filterByCategory(name: String?/*, addToBackStack: Boolean = false*/) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MoviesFragment.create(name))
            .commit()
    }
}