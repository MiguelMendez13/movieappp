package dataModelOverview

import com.tesoem.movieapp.APIresponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIservicesOver {
    @GET
    suspend fun getData(@Url url:String): Response<Overview>
}