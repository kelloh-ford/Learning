package com.example.nestedvschainedswitchmap

import android.app.Activity
import android.os.Bundle
import com.example.nestedvschainedswitchmap.Providers.PollingObservableProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val banner = Banner(this, banner_container)
        val pollingObservableProvider = PollingObservableProvider(
            this,
            progress_bar,
            banner,
            trailer_light_check_button
        )

        val compositeDisposable = CompositeDisposable()

        // Change from chained to nested to view difference in behavior
        compositeDisposable.add(
            pollingObservableProvider.nestedPollingObservable
                .subscribe({},Throwable::printStackTrace))
    }



}