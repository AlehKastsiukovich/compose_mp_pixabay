package data.network.util

internal sealed class Response<out S, out E : Throwable> {
    internal class Success<S>(val data: S) : Response<S, Nothing>()

    internal class Error<E : Throwable>(val error: E) : Response<Nothing, E>()
}
