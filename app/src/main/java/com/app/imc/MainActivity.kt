package com.app.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    // Drawer Layout
    //val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    // Fragments
    private lateinit var navigationView: NavigationView
    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atribui Toolbar
        toolbar = findViewById(R.id.toolbar_main)
        // Define a barra de ferramentas como a barra de apps para a atividade. Por padrão, a barra de ações contém apenas o nome do app e um menu flutuante
        setSupportActionBar(toolbar)

        // Botão voltar - pode chamar a função nativa onSupportNavigateUp()
        // supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initDrawer()
        initFragments()
    }

    // Atribui Drawer Layout a Toolbar
    private fun initDrawer() {
        // Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Toogle - icone de menu
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    // Inicia Fragment Layout
    private fun initFragments() {
        navigationView = findViewById(R.id.nav_view)
        navigationController = findNavController(R.id.host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            // id do item no menu tem que ser o mesmo id do fragment em navigation
            R.id.homeFragment, R.id.calculatorFragment, R.id.historyFragment
        ), drawerLayout)
        setupActionBarWithNavController(navigationController, appBarConfiguration)
        navigationView.setupWithNavController(navigationController)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
