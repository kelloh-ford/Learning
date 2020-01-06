package com.ford.rxdragobservable

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

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


    private  var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.image)



        val touch = imageView.longPressObservable.share()               //can use imageView.touches().share(), no need to create longPressObservable
        val touchDownGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_DOWN }
                .log(Emoji.down)
        val moveGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_MOVE }
                .log(Emoji.move)
        val touchUpGesture =
            touch.filter { motionEvent -> motionEvent.action == MotionEvent.ACTION_UP }
                .log(Emoji.up)



       disposable =  touchDownGesture.concatMap { initialTouch ->

            val firstCoordinate = Pair(initialTouch.x, initialTouch.y)

            moveGesture.map { moveCoordinate ->

                val x = moveCoordinate.x - firstCoordinate.first
                val y = moveCoordinate.y - firstCoordinate.second
                Pair(x, y)

            }.takeUntil(touchUpGesture)
        }.subscribe {

            imageView.x = imageView.x + it.first
            imageView.y = imageView.y + it.second

        }


    }


    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()

    }


}
