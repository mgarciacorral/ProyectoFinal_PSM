package es.uva.psm.ProyectoFinal_PSM

import es.uva.sg.psm.peliculasapp.servicioAPIPelicula

class RepositorioPeliculas {
    suspend fun getPeliculas(): ListaPeliculasRespuesta {
        return servicioAPIPelicula.getPeliculas()
    }

    suspend fun getDetallesPelicula(id: Long): DetallesPelicula {
        return servicioAPIPelicula.getPeliculaPorId(id)
    }

    suspend fun buscarPelicula(query: String): ListaPeliculasRespuesta {
        return servicioAPIPelicula.buscarPelicula(query = query)
    }
}