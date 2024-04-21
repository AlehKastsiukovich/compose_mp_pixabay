package org.alehkastsiukovich.pixabay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.network.ktor.HttpClientProviderImpl
import data.network.ktor.PixabayApiImpl
import data.network.ktor.PlatformHttpClientEngineFactory
import data.network.repository.PixabayRepositoryImpl
import kotlinx.coroutines.Dispatchers
import ui.App
import ui.viewmodel.SearchImagesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo = PixabayRepositoryImpl(
            PixabayApiImpl(
                HttpClientProviderImpl(PlatformHttpClientEngineFactory().engineFactory)
            )
        )

        val viewModel = SearchImagesViewModel(
            repo, Dispatchers.Default
        )

        setContent {
            App(viewModel.state)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}
