package com.example.githubrepos.ui.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepos.model.Repository

class RepositoriesViewModel() : ViewModel() {

    private val _repoList = MutableLiveData<MutableList<Repository>>()
    val repoList: LiveData<MutableList<Repository>> get() = _repoList

    init {
        //_repoList.value = mutableListOf()
        getRepositories()
    }


    private fun getRepositories(){
        _repoList.value = arrayListOf(
            Repository("501","Harshita","Harshita@gmail.com", "", "", 5,8,1),
            Repository("502","minu","minu@gmail.com","", "", 6,8,4),
            Repository("503","ram","ram@gmail.com","", "", 1,8,6),
            Repository("504","sham","sham@gmail.com","", "", 5,3,1),
            Repository("505","raja","raja@gmail.com","", "", 1,1,8),
            Repository("506","harsh","harsh@gmail.com","", "", 7,1,10),
            Repository("507","harshu","harshu@gmail.com","", "", 0,8,6),
            Repository("508","xyz","xyz@gmail.com","", "", 3,2,0),
            Repository("509","abc","abc@gmail.com","", "", 8,0,0),
            Repository("510","pqr","pqr@gmail.com","", "", 4,6,1),
        )
    }
}