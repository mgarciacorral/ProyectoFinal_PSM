package es.uva.psm.ProyectoFinal_PSM

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaPrincipal(navController: NavController, repositorio: RepositorioPeliculas) {
    var peliculas by remember { mutableStateOf<List<Pelicula>>(emptyList()) }

    LaunchedEffect(Unit) {
        peliculas = repositorio.getPeliculas().results
    }

    Scaffold(
        topBar = {
            BarraArriba()
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
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
            Text(text = pelicula.overview ?: "")
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
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .heightIn(max = 24.dp)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.app_bar_color)
        )
    )
}