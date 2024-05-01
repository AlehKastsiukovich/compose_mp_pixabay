package presentation.model

import androidx.compose.runtime.Stable

@Stable
sealed class UISearchImagesState  {
    @Stable
    data class FullListState(
        val images: List<UISingleImage> = emptyList(),
        val isLoading: Boolean = false
    ) : UISearchImagesState()
    @Stable
    data class ErrorDateState(val errorMessage: String?) : UISearchImagesState()
}


@Stable
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