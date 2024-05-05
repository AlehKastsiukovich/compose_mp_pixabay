package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Column {
                    FrontImage(
                        image = imageListState.frontImage,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                    HitImagesGrid(
                        images = imageListState.images,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                SearchWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    onSearch = {}
                )
            }
        }
    }
}

@Composable
fun FrontImage(image: UISingleImage?, modifier: Modifier) {
    image?.let { uiSingleImage ->
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(uiSingleImage.middleImageUrl)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Composable
fun HitImagesGrid(images: List<UISingleImage>, modifier: Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(4),
        modifier = modifier,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(modifier: Modifier, onSearch: (String) -> Unit) {
    val searchText = remember { mutableStateOf("") }
    val isActive = remember { mutableStateOf(false) }

    SearchBar(
        modifier = modifier,
        query = searchText.value,
        onQueryChange = { searchText.value = it },
        onSearch = { searchText.value = it },
        active = false,
        onActiveChange = {
            isActive.value = it
        },
        placeholder = {
            Text(text = "Search for all image on Pixabay")
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clickable {
                        searchText.value = ""
                    },
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onSearch(searchText.value)
                    },
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        content = {}
    )
}

@Composable
fun ErrorScreen() {}
