package es.uva.psm.ProyectoFinal_PSM

data class Pelicula(
    val id: Int,
    val title: String,
    val overview: String?,
    val release_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val poster_path: String?,
    val backdrop_path: String?,
    val original_language: String?,
    val original_title: String?,
    val popularity: Double?
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
    val overview: String?,
    val release_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val original_language: String,
    val original_title: String,
    val popularity: Double,
    val genres: List<Genero>,
    val runtime: Int?,
    val status: String,
    val tagline: String?,
    val homepage: String?,
    val budget: Int,
    val revenue: Int
)

data class Genero(
    val id: Int,
    val name: String
)
