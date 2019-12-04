package com.ford.dragobservable


import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imageView = findViewById<ImageView>(R.id.image)


        val touchEventObservable = Observable.create<MotionEvent> { emitter ->

            imageView.setOnTouchListener { v, event ->
                emitter.onNext(event)
                true
            }

        }

        val touchDownGesture =
            touchEventObservable.log().filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_DOWN }

        val moveGesture = touchEventObservable.log().filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_MOVE }

        val touchUpGesture =
            touchEventObservable.log().filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_UP }


        touchDownGesture.concatMap { motionEvent -> moveGesture.takeUntil(touchUpGesture)}.subscribe { motionEvent ->
            imageView.x = motionEvent.rawX
            imageView.y = motionEvent.rawY
        }


    }


}
