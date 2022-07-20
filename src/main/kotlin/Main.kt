import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

fun main() {

    val urlApi = URI.create("https://alura-filmes.herokuapp.com/conteudos")

    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(urlApi)
        .GET()
        .build()

    val response = httpClient.send(request, BodyHandlers.ofString())
    val jsonNode = Gson().fromJson(response.body(), JsonObject::class.java)
    val movies = Gson().fromJson(jsonNode["items"], Array<Movie>::class.java)
//    FigureFactory().create(URL(bigPictureUrl(movies[1].image)).openStream(), movies[1].title)
//    bigPictureUrl(movies[0].image)

    movies.forEach {
        println(it.title)
        FigureFactory().create(URL(bigPictureUrl(it.image)).openStream(), it.title)
    }
}
private fun bigPictureUrl(imageUrl: String): String {
    println(imageUrl)
    if(imageUrl.contains("@")) {
        val imageType = imageUrl.substringAfterLast(".")
        val urlEdited = imageUrl.substring(0, imageUrl.lastIndexOf("@") + 1)
        val urlFinal =  "$urlEdited.$imageType"
        println(urlFinal)
        return urlFinal
    }
    return imageUrl
}