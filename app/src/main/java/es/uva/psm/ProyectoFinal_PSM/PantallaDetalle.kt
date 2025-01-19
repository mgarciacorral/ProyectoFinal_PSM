package es.uva.psm.ProyectoFinal_PSM

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text(text = "Fecha de lanzamiento: ${pelicula.release_date}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Calificación: ${pelicula.vote_average}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Resumen:", style = MaterialTheme.typography.titleMedium)
            Text(text = pelicula.overview ?: "No hay resumen disponible", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Géneros:", style = MaterialTheme.typography.titleMedium)
            pelicula.genres.forEach { genero ->
                Text(text = genero.name, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Duración: ${pelicula.runtime} minutos", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Estado: ${pelicula.status}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Eslogan: ${pelicula.tagline ?: "No hay eslogan disponible"}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Página web: ${pelicula.homepage ?: "No hay página web disponible"}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Presupuesto: \$${pelicula.budget}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ingresos: \$${pelicula.revenue}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${pelicula.poster_path}",
                    contentDescription = "Poster de la película",
                    modifier = Modifier.size(400.dp)
                )
            }
        }
    }
}