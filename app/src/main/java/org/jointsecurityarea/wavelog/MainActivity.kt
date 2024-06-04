package org.jointsecurityarea.wavelog

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.jointsecurityarea.wavelog.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // User Code
        Log.i(TAG, "Starting App")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val call = ApiClient.apiService.getStations(RetrofitClient.API_KEY)

            call.enqueue(object : Callback<List<Station>> {
                override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
                    if (response.isSuccessful) {
                        val stationinfo = response.body()
                        // Handle the retrieved post data
                        Log.i(TAG, "Success! ${stationinfo.toString()}")
                        //Toast.makeText(this@MainActivity, "Success! ${stationinfo.toString()}", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle error
                        Log.i(TAG, "Error: ${response.errorBody()}")
                        //Toast.makeText(this@MainActivity, "Error: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                    // Handle failure
                    if (t is IOException) {
                        Log.i(TAG, "Network failure")
                    }
                    else
                    {
                        Log.i(TAG, "Conversion Error")
                    }
                    //Toast.makeText(this@MainActivity, "Request Fail", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}