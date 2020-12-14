package com.hul.brainvire.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.viewmodel.ListViewModel

class ViewModelFactory(private val listRepository: ListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return ListViewModel(listRepository) as T
    }
}