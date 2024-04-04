package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch {
//        val provider = HttpClientProviderImpl(PlatformHttpClientEngineFactory().engineFactory)
//        val api = PixabayApiImpl(provider)
//        api.getImages()
//    }
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}
