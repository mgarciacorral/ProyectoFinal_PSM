package es.uva.psm.ProyectoFinal_PSM

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.uva.psm.ProyectoFinal_PSM.ui.theme.ProyectoFinal_PSMTheme

class VistaModeloPelicula : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinal_PSMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MyApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    val repositorio = RepositorioPeliculas()

    NavHost(navController = navController, startDestination = "pantallaPrincipal") {
        composable("pantallaPrincipal") {
            PantallaPrincipal(navController = navController, repositorio = repositorio)
        }
        composable("pantallaDetalle/{id}") { backStackEntry ->
            backStackEntry.arguments?.getString("id")
                ?.let { PantallaDetalle(navController = navController, id = it, repositorio = repositorio) }
        }
    }
}
