package data.network.repository

import data.network.util.AppDataError
import data.network.util.Result

internal interface PixabayRepository {
    suspend fun getSearchedImages(): Result<List<ImageHitModel>, AppDataError>
}

internal data class ImageHitModel(
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
