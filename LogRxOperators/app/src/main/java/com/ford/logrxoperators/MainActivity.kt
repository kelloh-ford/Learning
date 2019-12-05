package com.ford.logrxoperators

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.ford.logrxoperators.R
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class MainActivity:Activity()
{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        var obsA = Observable.timer(3,TimeUnit.SECONDS).map { _ -> 1}.log(Emoji.Smile)

        var obsB = Observable.timer(5,TimeUnit.SECONDS).log(Emoji.Heart)

        var obsC = Observable.timer(7,TimeUnit.SECONDS).log(Emoji.Building)


        obsA.takeUntil(obsB).takeUntil(obsC).subscribe {


            println(it)


        }






    }










}