package data.network.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual class PlatformHttpClientEngineFactory {
    actual val engineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
}
