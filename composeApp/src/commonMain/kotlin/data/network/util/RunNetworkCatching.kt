package data.network.util

internal suspend fun <T> runNetworkCatching(action: suspend () -> T): Result<T, AppDataError> {
    return runCatching {
        action.invoke()
    }.fold(
        onSuccess = { result: T -> Result.Success(result) },
        onFailure = { Result.Error(AppDataError.NetworkDataError.Common) }
    )
}
