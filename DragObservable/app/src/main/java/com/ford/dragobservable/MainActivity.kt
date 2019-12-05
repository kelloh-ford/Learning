package com.ford.dragobservable

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import io.reactivex.Observable

val ImageView.longPressObservable: Observable<MotionEvent>
    get() =
        Observable.create { emitter ->
            View.OnTouchListener { _, event ->


                event?.let {

                    emitter.onNext(it)

                }
                true
            }.let {
                setOnTouchListener(it)
            }
            emitter.setCancellable {
                setOnTouchListener(null)
            }
        }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.image)


        val touch = imageView.longPressObservable.share()
        val touchDownGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_DOWN }
                .log(Emoji.down)
        val moveGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_MOVE }
                .log(Emoji.move)
        val touchUpGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_UP }
                .log(Emoji.up)


        touchDownGesture
            .concatMap { moveGesture.takeUntil(touchUpGesture) }
            .subscribe { motionEvent ->

                imageView.x = (motionEvent.x + imageView.x) - imageView.width/2f
                imageView.y = (motionEvent.y + imageView.y) - imageView.height/2f


            }




    }
}