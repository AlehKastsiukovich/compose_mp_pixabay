package data.network.repository

import data.network.ktor.PixabayApi
import data.network.util.AppDataError
import data.network.util.Result
import data.network.util.runNetworkCatching

internal class PixabayRepositoryImpl(
    private val pixabayApi: PixabayApi
) : PixabayRepository {
    override suspend fun getSearchedImages(): Result<List<ImageHitModel>, AppDataError> {
        return runNetworkCatching {
            pixabayApi.getImages()
                .hits
                .map { imageResponse ->
                    imageResponse.toImageHitModel()
                }
        }
    }
}
