package com.example.nestedvschainedswitchmap.utils

import android.util.Log
import io.reactivex.*

/**
Example usage of [log] in Kotlin:
Single.timer(1, TimeUnit.SECONDS)
.log(Emoji.Plane)
.subscribe({ }, { })

---------------------------------------------------------------------------

Example usage of [log] in Java:
Single.timer(1,TimeUnit.SECONDS)
.compose(upstream ->  RxOperatorLoggerKt.log(upstream,Emoji.Companion.Plane))
.subscribe({ }, { })

In order to be able to use these functions, you must add a dependency to rxutils by
pasting this into your feature's build.gradle file: implementation project(':rxutils')

 */


/**
 * Adds a log that prints out the events of this source(Single/Maybe/Flowable/Observable/Completable)
 * @param tag the string used to identify the source - must not be null, and Emoji's can be used
 */
fun <T> Single<T>.log(tag: String): Single<T> {
    return doOnSubscribe { println("$tag Subscribe ${Emoji.Plane}") }
        .doOnSuccess {println("$tag Success: ${Emoji.completed} :  $it") }
        .doOnError { println("$tag Error ${Emoji.error} $it") }
        .doOnDispose { println("$tag Dispose ${Emoji.disposed}") }
}

fun <T> Maybe<T>.log(tag: String): Maybe<T> {
    return doOnSubscribe { println("$tag Subscribe ${Emoji.Plane}") }
        .doOnComplete {println("$tag Completed ${Emoji.completed}") }
        .doOnSuccess {println("$tag Success: ${Emoji.completed} :  $it") }
        .doOnError { println("$tag Error ${Emoji.error} $it") }
        .doOnDispose { println("$tag Disposed ${Emoji.disposed}") }
}

fun Completable.log(tag: String): Completable {
    return doOnSubscribe { println("$tag Subscribe ${Emoji.Plane}") }
        .doOnComplete { println("$tag onComplete ${Emoji.completed}") }
        .doOnError { println("$tag onError ${Emoji.error} $it") }
        .doOnDispose { println("$tag Dispose ${Emoji.disposed}") }
}

fun <T> Observable<T>.log(tag: String): Observable<T> {
    return doOnSubscribe { println("$tag Subscribe ${Emoji.Plane}") }
        .doOnNext { println("$tag onNext: ${Emoji.next} :  $it") }
        .doOnComplete { println("$tag onComplete ${Emoji.completed}") }
        .doOnError { println("$tag onError ${Emoji.error} $it") }
        .doOnDispose { println("$tag Dispose ${Emoji.disposed}") }
}

fun <T> Flowable<T>.log(tag: String): Flowable<T> {
    return doOnSubscribe { println("$tag Subscribe ${Emoji.Plane}") }
        .doOnNext { println("$tag onNext: ${Emoji.next} :  $it") }
        .doOnCancel { println("$tag Cancel ${Emoji.disposed}") }
        .doOnComplete { println("$tag onComplete ${Emoji.completed}") }
        .doOnError { println("$tag onError ${Emoji.error} $it") }
}
