package com.tesoem.movieapp
import retrofit2.Response
import  retrofit2.http.GET
import  retrofit2.http.Url
interface APIservice {
    @GET
    suspend fun getData(@Url url:String):Response<APIresponse>
}