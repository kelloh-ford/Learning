package com.example.nestedvschainedswitchmap.Providers

import com.example.nestedvschainedswitchmap.model.GithubResponse
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class NetworkCallProvider(private val githubRepositoryProvider: GithubRepositoryProvider) {

    fun getPollingObservable(vin:String) =
        Observable.interval(7, TimeUnit.SECONDS).take(2)
            .switchMap { makeNetworkCall(vin) }

    private fun makeNetworkCall(repoName: CharSequence): Observable<GithubResponse> {
        return githubRepositoryProvider.getRepositories(repoName)
    }
}
