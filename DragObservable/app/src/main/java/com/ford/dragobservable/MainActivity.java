//package com.ford.dragobservable;
//
//
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.widget.ImageView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.jakewharton.rxbinding3.view.RxView;
//
//public class MainActivity extends AppCompatActivity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        ImageView imageView = findViewById(R.id.image);
//
//
//        RxView.touches(imageView)
//                .filter(motionEvent -> motionEvent.getAction() == MotionEvent.ACTION_DOWN)
//                .flatMap(downTouch -> RxView.touches(imageView)
//                        .filter(motionEvent -> motionEvent.getAction() == MotionEvent.ACTION_MOVE))
//                .takeUntil(RxView.touches(imageView).filter(motionEvent -> motionEvent.getAction() == MotionEvent.ACTION_UP))
//                .subscribe(motionEvent -> {
//                    imageView.setX(motionEvent.getRawX() - imageView.getPivotX());
//                    imageView.setY(motionEvent.getRawY() - (imageView.getPivotY() * 2));
//                });
//
//
//        // This also works. No need to filter by action type?
//
////        RxView.touches(imageView)
////                .concatMap(downTouch -> RxView.touches(imageView))
////                .takeUntil(RxView.touches(imageView)).
////                subscribe(motionEvent -> {
////
////                    imageView.setX(motionEvent.getRawX() - imageView.getPivotX());
////                    imageView.setY(motionEvent.getRawY() - (imageView.getPivotY() * 2));
////
////                });
//
//
//    }
//
//
//}
