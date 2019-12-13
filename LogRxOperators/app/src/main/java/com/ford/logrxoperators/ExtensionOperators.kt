package com.ford.logrxoperators

import io.reactivex.Observable


/*

The enumerated function takes in an observable with values of type T. It will then emit
pairs that contain the value along with its index.

For example, given an observable with these values ("John","Mary","Adam"), enumerated will return an observable
with the values (0,"John"),(1,"Mary"),(2,"Adam")

The enumerated operator is an extension operator that does not exist in RxKotlin.

For more information, go to https://developer.apple.com/documentation/swift/array/1687832-enumerated

Note: The example is in swift, but the idea is still the same.

*/

fun <T : Any> Observable<T>.enumerated(): Observable<Pair<Int, T>> =
    scan(Pair<Int, T?>(-1, null)) { oldValue, newValue ->
        Pair(oldValue.first + 1, newValue)
    }
        .flatMapIterable { value ->
            listOfNotNull(value.first).zip(listOfNotNull(value.second))
        }






