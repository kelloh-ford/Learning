package com.ford.logrxoperators

/*

 Test the behaviour of observables, operators, singles, and so on by using the
 log function to print the status of an observable at each point of its "lifecycle"

 The log function uses Emoji's in order to make logging easier. You can change the emoji being
 displayed by passing your Emoji of choice into the log function.


 */


fun main() {


    //takeUntil

    firstObservableCompletesBeforeSecondStartsTakeUntil()
    firstObservableFiresFirstTakeUntil()
    secondObservableFiresFirstTakeUntil()




}






