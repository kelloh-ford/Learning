package com.ford.logrxoperators

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


/*

 Test the behaviour of observables, operators, singles, and so on by using the
 log function to print the status of an observable at each point of its "lifecycle"

 The log function uses Emoji's in order to make logging easier. You can change the emoji being
 displayed by passing your Emoji of choice into the log function.

 */





fun main() {


    val obsA = Observable.interval(2, TimeUnit.SECONDS).log(Emoji.completed)

    val obsB = Observable.interval(10, TimeUnit.SECONDS).log(Emoji.b_emoji)


    obsA.takeUntil(obsB).subscribe {

    }

    Thread.sleep(11000)

}

