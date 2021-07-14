package com.example.nestedvschainedswitchmap.Providers

import com.example.nestedvschainedswitchmap.model.GithubResponse
import com.example.nestedvschainedswitchmap.Modules.RetrofitModule
import com.example.nestedvschainedswitchmap.Services.GithubRepositoryService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GithubRepositoryProvider {

    private val retrofit = RetrofitModule().getRetrofitClient().create(GithubRepositoryService::class.java)

    fun getRepositories(repoName: CharSequence): Observable<GithubResponse> {
        return retrofit.getRepositories(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}