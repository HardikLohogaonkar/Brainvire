package com.hul.brainvire.repository

import com.hul.brainvire.network.NetworkApiClient

class ListRepository {

    suspend fun getHistoryData() =
        NetworkApiClient.getNetworkServices().getHistory()
}