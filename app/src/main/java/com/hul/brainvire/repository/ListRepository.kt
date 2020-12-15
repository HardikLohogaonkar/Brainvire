package com.hul.brainvire.repository

import com.hul.brainvire.network.NetworkApiClient

class ListRepository {

    suspend fun getHistoryData(startAt: String, endAt: String, base: String) =
        NetworkApiClient.getNetworkServices().getHistory(startAt, endAt, base)
}