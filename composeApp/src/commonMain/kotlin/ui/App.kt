package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.network.ktor.HttpClientProviderImpl
import data.network.ktor.PixabayApiImpl
import data.network.ktor.PlatformHttpClientEngineFactory
import data.network.repository.PixabayRepositoryImpl
import data.network.util.Result
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val coroutineScope = rememberCoroutineScope()

    val repo = PixabayRepositoryImpl(
        PixabayApiImpl(
            HttpClientProviderImpl(PlatformHttpClientEngineFactory().engineFactory)
        )
    )

    coroutineScope.launch {
        when (val result = repo.getSearchedImages()) {
            is Result.Error -> println()
            is Result.Success -> {
                println("SIZE C" + result.data.size)
            }
        }
    }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}
