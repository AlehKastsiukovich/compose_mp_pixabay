package data.network.ktor

import io.ktor.client.call.body
import io.ktor.client.request.get

internal class PixabayApiImpl(
    private val httpClientProvider: HttpClientProvider
) : PixabayApi {

    override suspend fun getImages(): HitResponse {
        return httpClientProvider.httpClient.get(PIXABAY_BASE_URL + "api/") {
            url {
                parameters.append("key", "42856300-e8e64c17d67878a4c639d42a0")
                parameters.append("per_page", 50.toString())
                parameters.append("page", 1.toString())
            }
        }.body()
    }

    companion object {
        private const val PIXABAY_BASE_URL = "https://pixabay.com/"
    }
}
