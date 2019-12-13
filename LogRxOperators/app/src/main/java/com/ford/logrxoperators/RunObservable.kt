package com.ford.logrxoperators

import io.reactivex.Observable
import io.reactivex.internal.operators.observable.BlockingObservableIterable

fun <T> demoUntilComplete(observable: Observable<T>, purpose: String = "Begin Demo", printResults:Boolean = false) {
    println("\n=======$purpose========\n")
    BlockingObservableIterable(observable, 200).forEach {

       if(printResults)
           println(it)
    }
}