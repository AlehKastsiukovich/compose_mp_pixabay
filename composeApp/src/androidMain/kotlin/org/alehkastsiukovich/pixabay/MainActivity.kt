package org.alehkastsiukovich.pixabay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import data.network.ktor.HttpClientProviderImpl
import data.network.ktor.PixabayApiImpl
import data.network.ktor.PlatformHttpClientEngineFactory
import data.network.repository.PixabayRepositoryImpl
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTheme
import io.github.alexzhirkevich.cupertino.adaptive.CupertinoThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.MaterialThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.Theme
import kotlinx.coroutines.Dispatchers
import presentation.viewmodel.SearchImagesViewModel
import ui.App

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAdaptiveApi::class)
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
            AdaptiveTheme(
                target = Theme.Material3,
                material = MaterialThemeSpec.Default(),
                cupertino = CupertinoThemeSpec.Default(),
                content = { App(viewModel.state) }
            )
        }
    }
}