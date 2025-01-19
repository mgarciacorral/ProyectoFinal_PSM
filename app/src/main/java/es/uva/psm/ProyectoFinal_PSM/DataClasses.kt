package es.uva.psm.ProyectoFinal_PSM

data class Pelicula(
    val id: Int,
    val title: String,
    val overview: String? = "No hay resumen disponible",
    val release_date: String? = "No hay fecha de lanzamiento",
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
    val poster_path: String? = "No hay poster disponible",
    val backdrop_path: String? = "No hay backdrop disponible",
    val original_language: String? = "No hay idioma disponible",
    val original_title: String? = "No hay título original disponible",
    val popularity: Double? = 0.0,
)

data class ListaPeliculasRespuesta(
    val page: Int,
    val results: List<Pelicula>,
    val total_results: Int,
    val total_pages: Int
)

data class DetallesPelicula(
    val id: Int,
    val title: String,
    val overview: String? = "No hay resumen disponible",
    val release_date: String? = "No hay fecha de lanzamiento",
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String? = "No hay poster disponible",
    val backdrop_path: String? = "No hay backdrop disponible",
    val original_language: String,
    val original_title: String,
    val popularity: Double,
    val genres: List<Genero>,
    val runtime: Int? = 0,
    val status: String,
    val tagline: String? = "No hay tagline disponible",
    val homepage: String? = "No hay web disponible",
    val budget: Int? = 0,
    val revenue: Int?= 0
)

data class Genero(
    val id: Int,
    val name: String
)
