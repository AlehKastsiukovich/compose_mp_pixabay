package ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.network.repository.ImageHitModel
import data.network.repository.PixabayRepository
import data.network.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SearchImagesViewModel(
    private val pixabayRepository: PixabayRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state: MutableStateFlow<UISearchImageState?> = MutableStateFlow(null)
    val state: StateFlow<UISearchImageState?> = _state

    init {
        viewModelScope.launch(dispatcher) {
            when (val result = pixabayRepository.getSearchedImages()) {
                is Result.Error -> {
                    UISearchImageState(
                        images = listOf(),
                        errorMessage = ""
                    )
                }

                is Result.Success -> {
                    _state.value = UISearchImageState(
                        images = result.data.map { imageHitModel ->
                          imageHitModel.mapToUISingleImage()
                        },
                        errorMessage = null
                    )
                }
            }
        }

    }

}

data class UISearchImageState(
    val images: List<UISingleImage>,
    val errorMessage: String?
)

data class UISingleImage(
    val imageId: Long,
    val userId: Long,
    val userName: String,
    val tags: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val previewUrl: String,
    val previewHeight: Int,
    val previewWidth: Int,
    val middleImageUrl: String,
    val largeImageUrl: String
)

internal fun ImageHitModel.mapToUISingleImage(): UISingleImage {
    return UISingleImage(
        imageId = this.imageId,
        userId = this.userId,
        userName = this.userName,
        tags = this.tags,
        likes = this.likes,
        downloads = this.downloads,
        comments = this.comments,
        previewUrl = this.previewUrl,
        previewHeight = this.previewHeight,
        previewWidth = this.previewWidth,
        middleImageUrl = this.middleImageUrl,
        largeImageUrl = this.largeImageUrl
    )
}
