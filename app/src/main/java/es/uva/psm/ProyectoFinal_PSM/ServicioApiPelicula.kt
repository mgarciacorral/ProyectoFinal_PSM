package es.uva.sg.psm.peliculasapp

import es.uva.psm.ProyectoFinal_PSM.DetallesPelicula
import es.uva.psm.ProyectoFinal_PSM.ListaPeliculasRespuesta
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.random.Random

private val client = OkHttpClient.Builder().addInterceptor { chain ->
    val request: Request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNTUzNTQ3MTY5NjNkOGE0NGEyOTI0Zjk1NWM3Y2YyYSIsIm5iZiI6MTczNzI4NjIwNS4wMDgsInN1YiI6IjY3OGNlMjNkOGJjYTY2MWQwNTQzMzgyZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.l77jqwuRvRoOGIapexTlhknMFfblxUbCL455mJbspRc")
        .build()
    chain.proceed(request)
}.build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val servicioAPIPelicula = retrofit.create(ServicioAPIPelicula::class.java)

interface ServicioAPIPelicula {

    @GET("movie/top_rated")
    suspend fun getPeliculas(
        @Query("api_key") apiKey: String = "a55354716963d8a44a2924f955c7cf2a",
        @Query("language") language: String = "es-ES",
        @Query("page") pagina: Int = Random.nextInt(1, 20)
    ): ListaPeliculasRespuesta

    @GET("movie/{id}")
    suspend fun getPeliculaPorId(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = "a55354716963d8a44a2924f955c7cf2a",
        @Query("language") language: String = "es-ES"
    ): DetallesPelicula

    @GET("search/movie")
    suspend fun buscarPelicula(
        @Query("api_key") apiKey: String = "a55354716963d8a44a2924f955c7cf2a",
        @Query("language") language: String = "es-ES",
        @Query("query") query: String,
        @Query("page") pagina: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): ListaPeliculasRespuesta
}