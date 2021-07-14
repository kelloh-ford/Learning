package com.example.nestedvschainedswitchmap.Services

import com.example.nestedvschainedswitchmap.model.GithubResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubRepositoryService {


    @GET("repositories")
    fun getRepositories(@Query("q") repositoryName: CharSequence): Observable<GithubResponse>


}