package data.network.util

internal sealed interface AppDataError : AppError {
    sealed class NetworkDataError : AppDataError {
        data object Common : AppDataError
    }
    data object LocalDataError : AppDataError
}
