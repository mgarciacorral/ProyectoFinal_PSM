package es.uva.psm.ProyectoFinal_PSM

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun PantallaDetalle(navController: NavController, id: String, repositorio: RepositorioPeliculas) {
    var pelicula by remember { mutableStateOf<DetallesPelicula>(DetallesPelicula(0, "", "", "", 0.0, 0, "", "", "", "", 0.0, emptyList(), 0, "", "", "", 0, 0)) }

    LaunchedEffect(Unit) {
        pelicula = repositorio.getDetallesPelicula(id.toLong())
    }

    Scaffold(
        topBar = {
            BarraArriba(title = pelicula.title)
        },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${pelicula.poster_path}",
                        contentDescription = "Poster de la película",
                        modifier = Modifier.size(400.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(text = "Fecha de lanzamiento:", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.release_date}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Calificacion:", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.vote_average}/10", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Resumen:", style = MaterialTheme.typography.titleMedium)
                Text(text = pelicula.overview ?: "No hay resumen disponible", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Géneros:", style = MaterialTheme.typography.titleMedium)
                pelicula.genres.forEach { genero ->
                    Text(text = genero.name, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Duración:", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.runtime} minutos", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Estado:", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.status}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Eslogan:", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.tagline ?: "No hay eslogan disponible"}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Página Web", style = MaterialTheme.typography.titleMedium)
                Text(text = "${pelicula.homepage ?: "No hay página web disponible"}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Presupuesto:", style = MaterialTheme.typography.titleMedium)
                Text(text = "\$${pelicula.budget}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ingresos:", style = MaterialTheme.typography.titleMedium)
                Text(text = "\$${pelicula.revenue}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}