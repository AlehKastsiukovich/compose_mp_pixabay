package data.network.util

internal sealed interface Result<out T, out E : AppError> {
    data class Success<out T, out E : AppError>(val data: T) : Result<T, E>
    data class Error<out T, out E : AppError>(val error: E) : Result<T, E>
}
