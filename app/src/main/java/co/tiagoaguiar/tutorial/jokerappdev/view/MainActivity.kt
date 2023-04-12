package co.tiagoaguiar.tutorial.jokerappdev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.tiagoaguiar.tutorial.jokerappdev.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()       //tem que ser antes do setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        // Buscar toolbar e set que eh toolbar de acao
        val toolbar = findViewById<Toolbar>(R.id.app_bar_main_toolbar)
        setSupportActionBar(toolbar)
        // Buscar drawer menu layout
        val drawerLayout = findViewById<DrawerLayout>(R.id.activity_main_drawer_layout)
        // Buscar a referencia do navview (na activity_main)
        val navView = findViewById<NavigationView>(R.id.activity_main_nav_view)
        // Pegar o controlador da navegação
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Inserir o hamburguer na barra de menu
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_joke_day, R.id.nav_about
            ), drawerLayout
        )
        // Fazer o sincronismo
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    //Associar o evento do clic no hamburguer
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}