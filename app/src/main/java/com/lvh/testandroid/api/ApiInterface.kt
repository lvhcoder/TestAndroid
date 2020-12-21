package com.lvh.testandroid.api

import com.lvh.testandroid.model.RepositoriesEntity
import com.lvh.testandroid.model.UserEntity
import com.lvh.testandroid.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by NguyenLinh on 02,October,2018
 */

interface ApiInterface {

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String?, @Field("password") password: String?): Observable<UserResponse>

    @GET("/api/")
    fun getUser(): Observable<UserResponse>

    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") userId: String): Observable<List<RepositoriesEntity>>
}