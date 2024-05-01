package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.model.UISearchImagesState
import presentation.model.UISingleImage

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
@Preview
fun App(state: StateFlow<UISearchImagesState>) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (val imageListState = state.collectAsState().value) {
            is UISearchImagesState.ErrorDateState -> {
                ErrorScreen()
            }

            is UISearchImagesState.FullListState -> {
                if (imageListState.isLoading) {
                    AdaptiveCircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                HitImagesGrid(imageListState.images)
            }
        }
    }
}

@Composable
fun HitImagesGrid(images: List<UISingleImage>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(
                items = images,
                key = { item: UISingleImage -> item.imageId }
            ) { uiSingleImage ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalPlatformContext.current)
                        .data(uiSingleImage.previewUrl)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .height(uiSingleImage.previewHeight.dp)
                        .width(uiSingleImage.previewWidth.dp)
                )
            }
        }
    )
}

@Composable
fun ErrorScreen() {}
