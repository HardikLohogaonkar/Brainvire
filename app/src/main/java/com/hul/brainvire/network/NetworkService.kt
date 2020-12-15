package com.hul.brainvire.network

import com.hul.brainvire.model.GetHistoryData
import com.hul.brainvire.network.NetworkConstant.GET_HISTORY

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET(GET_HISTORY)
    suspend fun getHistory(@Query("start_at") startAt:String,
                           @Query("end_at") endAt: String,
                           @Query("base") base: String): Response<GetHistoryData>
}