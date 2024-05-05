package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.network.repository.ImageHitModel
import data.network.repository.PixabayRepository
import data.network.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import presentation.model.UISearchImagesState
import presentation.model.UISingleImage

internal class SearchImagesViewModel(
    private val pixabayRepository: PixabayRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state: MutableStateFlow<UISearchImagesState> = MutableStateFlow(UISearchImagesState.FullListState())
    val state: StateFlow<UISearchImagesState> = _state

    init {
        defaultScreenState()
    }

    private fun defaultScreenState() {
        viewModelScope.launch(dispatcher) {
            _state.value = UISearchImagesState.FullListState(
                isLoading = true
            )
            when (val result = pixabayRepository.getSearchedImages()) {
                is Result.Error -> {
                    UISearchImagesState.ErrorDateState(
                        errorMessage = result.error.toString()
                    )
                }
                is Result.Success -> {
                    val images = result.data.map { imageHitModel ->
                        imageHitModel.mapToUISingleImage()
                    }
                    _state.value = UISearchImagesState.FullListState(
                        frontImage = images.firstOrNull(),
                        images = images.drop(1),
                        isLoading = false
                    )
                }
            }
        }
    }
}

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
