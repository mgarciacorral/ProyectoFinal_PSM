package es.uva.psm.ProyectoFinal_PSM

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PantallaPrincipal(navController: NavController, repositorio: RepositorioPeliculas) {
    var peliculas by remember { mutableStateOf<List<Pelicula>>(emptyList()) }
    val searchQuery = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            peliculas = repositorio.getPeliculas().results
        }
    }

    Scaffold(
        topBar = {
            BarraArriba()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        peliculas = repositorio.getPeliculas().results
                    }
                },
                containerColor = colorResource(id = R.color.app_bar_color)
            ) {
                Text("MÁS")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    CoroutineScope(Dispatchers.IO).launch {
                        peliculas = if (it.isEmpty()) {
                            repositorio.getPeliculas().results
                        } else {
                            repositorio.buscarPelicula(it).results
                        }
                    }
                },
                placeholder = { Text(text = "Buscar...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            LazyColumn {
                peliculas.let { peliculasList ->
                    items(peliculasList) { pelicula ->
                        PeliculaItem(pelicula = pelicula, onClick = {
                            navController.navigate("pantallaDetalle/${pelicula.id}")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun PeliculaItem(pelicula: Pelicula, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pelicula.title,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = pelicula.overview?.let {
                    if (it.length > 80) it.take(85) + "..." else it
                } ?: ""
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraArriba(
    title: String = "Pelis Guapas Q Lo Flipas"
) {
    SmallTopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.app_bar_color)
        ),
        modifier = Modifier.height(56.dp)
    )
}