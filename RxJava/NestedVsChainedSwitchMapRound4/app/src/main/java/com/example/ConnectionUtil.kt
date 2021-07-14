package com.example.nestedvschainedswitchmap

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class ConnectionUtil(private val context:Context) {

    fun getConnectionObservable(): Observable<Boolean> = Observable.create<Boolean>
    { emitter ->

        val connectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

        val networkCallBack = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                emitter.onNext(true)
            }

            override fun onLost(network: Network) {
                emitter.onNext(false)
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallBack)

        emitter.setCancellable { connectivityManager.unregisterNetworkCallback(networkCallBack) }

    }.observeOn(AndroidSchedulers.mainThread())
}

