package com.hul.brainvire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hul.brainvire.model.GetHistoryData
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel(private val mListRepository: ListRepository) : ViewModel() {

    val mData = MutableLiveData<Resource<GetHistoryData>>()

    fun getData(startAt: String, endAt: String, base: String) =
        viewModelScope.launch(Dispatchers.IO) {

            mData.postValue(Resource.Loading())
            val response = mListRepository.getHistoryData(startAt, endAt, base)
            mData.postValue(setData(response))
        }

    private fun setData(response: Response<GetHistoryData>): Resource<GetHistoryData> {

        if (response.isSuccessful) {
            response.body()?.let { it ->
                return Resource.Success(it)
            }
        }
        return Resource.Error(data = null, message = response.message())
    }
}