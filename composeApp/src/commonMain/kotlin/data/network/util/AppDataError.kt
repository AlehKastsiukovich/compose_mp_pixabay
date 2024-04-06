package data.network.util

internal sealed interface AppDataError : AppError {
    enum class NetworkDataError : AppDataError {
        COMMON_ERROR
    }
    object LocalDataError
}
