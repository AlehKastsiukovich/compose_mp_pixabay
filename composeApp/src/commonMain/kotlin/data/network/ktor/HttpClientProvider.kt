package data.network.ktor

import io.ktor.client.HttpClient

internal interface HttpClientProvider {
    val httpClient: HttpClient
}
