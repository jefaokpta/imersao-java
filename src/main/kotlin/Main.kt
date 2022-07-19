import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URI
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
    Gson().fromJson(jsonNode["items"], Array<Movie>::class.java).forEach { println(it) }

}