package com.ford.logrxoperators

import io.reactivex.Observable

/*

An example of enumerated. In this example, an iterable that contains the
values ("A", "B", "C", "D", "E") is transformed into:
(0, A)
(1, B)
(2, C)
(3, D)
(4, E)

 */

fun stringListEnumerated() = Observable.fromIterable(listOf("A", "B", "C", "D", "E")).enumerated()

