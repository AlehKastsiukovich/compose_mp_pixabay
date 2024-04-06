package data.network.repository

import data.network.ktor.ImageResponse

internal fun ImageResponse.toImageHitModel(): ImageHitModel {
    return ImageHitModel(
        imageId = this.imageId,
        userId = this.userId,
        tags = this.tags,
        userName = this.userName,
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
