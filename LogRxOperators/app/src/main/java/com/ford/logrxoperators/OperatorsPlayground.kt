package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {


    val obsA = Observable.interval(2, TimeUnit.SECONDS).log(Emoji.completed)

    var obsB = Observable.interval(10, TimeUnit.SECONDS).log(Emoji.b_emoji)


    obsA.takeUntil(obsB).subscribe {

    }

    Thread.sleep(11000)

}

