package com.example.nestedvschainedswitchmap.Providers

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.nestedvschainedswitchmap.Banner
import com.example.nestedvschainedswitchmap.ConnectionUtil
import com.example.nestedvschainedswitchmap.buttonObservable
import com.example.nestedvschainedswitchmap.utils.log
import io.reactivex.Observable
import java.net.UnknownHostException

class PollingObservableProvider(
    private val context: Context,
    private val progressBar: ProgressBar,
    private val banner: Banner,
    private val trailerLightCheckButton: Button
) {

    private val githubRepositoryProvider = GithubRepositoryProvider()
    private val vehicleStatusProvider = NetworkCallProvider(githubRepositoryProvider)
    private val connectionUtil = ConnectionUtil(context)

    private val buttonObservable = trailerLightCheckButton.buttonObservable

    private val pollingObservable = vehicleStatusProvider.getPollingObservable("vin")
        .doOnSubscribe { showProgressBar(); banner.hideBanner() }
        .doOnComplete { onPollingComplete() }
        .doFinally { hideProgressBar() }

    val nestedPollingObservable =
        connectionUtil.getConnectionObservable().log("connectionObservable")
                .switchMap { isConnected ->
                    if (isConnected) {
                                buttonObservable.log("buttonObservable")
                                    .switchMap {
                                        pollingObservable.log("pollingObservable")
                                    }
                    }
                    else {
                        Observable.empty()
                    }
                }.doOnError(this::onError)

    val chainedPollingObservable =
        connectionUtil.getConnectionObservable().log("connectionObservable")
            .switchMap { isConnected ->
                if(isConnected)
                    buttonObservable.log("buttonObservable")
                else
                    Observable.empty()
            }
            .log("connection+buttonObservable")
            .switchMap { pollingObservable.log("pollingObservable") }
            .doOnError(this::onError)











    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onError(throwable: Throwable) {
        if (throwable is UnknownHostException) {
            banner.showNoConnectionBanner()
        } else {
            banner.showGenericErrorBanner()
        }
        throwable.printStackTrace()
    }

    private fun onPollingComplete() {
        banner.showTlcSuccessBanner()
    }

}