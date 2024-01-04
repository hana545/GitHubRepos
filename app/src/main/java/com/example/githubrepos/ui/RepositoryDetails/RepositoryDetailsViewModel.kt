package com.example.githubrepos.ui.RepositoryDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepos.model.Repository

class RepositoryDetailsViewModel() : ViewModel() {

    private val _repoLiveData = MutableLiveData<Repository?>()
    val repoLiveData: LiveData<Repository?> = _repoLiveData
}