package com.hul.brainvire.network

import com.hul.brainvire.model.GetHistoryData
import com.hul.brainvire.network.NetworkConstant.GET_HISTORY

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET(GET_HISTORY)
    suspend fun getHistory(): Response<GetHistoryData>
}