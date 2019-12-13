package com.ford.logrxoperators

import io.reactivex.Observable

/*

 Test the behaviour of observables, operators, singles, and so on by using the
 log function to print the status of an observable at each point of its "lifecycle"

 The log function uses Emoji's in order to make logging easier. You can change the emoji being
 displayed by passing your Emoji of choice into the log function.


 */


fun main() {

    //takeUntil

    demoUntilComplete(firstObservableCompletesBeforeSecondStartsTakeUntil(),"First observable completes before second starts:")
    demoUntilComplete(firstObservableFiresFirstTakeUntil(),"First observable fires before the second observable:")
    demoUntilComplete(secondObservableFiresFirstTakeUntil(),"Second observable fires before the first observable:")

   // enumerated (custom operator)

    demoUntilComplete(stringListEnumerated(),"Enumerating a list of strings: ",printResults = true)



}








