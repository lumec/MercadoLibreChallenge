package com.lumec.challenge.mercadolibre.framework

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.lumec.challenge.domain.Error
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server
    else -> Error.Unknown
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    FirebaseCrashlytics.getInstance().recordException(e)
    Timber.e(e)
    e.toError().left()
}