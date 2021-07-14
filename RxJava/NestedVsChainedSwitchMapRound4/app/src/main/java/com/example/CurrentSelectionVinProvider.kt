package com.example.nestedvschainedswitchmap

import io.reactivex.Observable

class CurrentSelectionVinProvider {

    fun getCurrentSelectedVin(): Observable<String> = Observable.just("vin")

}