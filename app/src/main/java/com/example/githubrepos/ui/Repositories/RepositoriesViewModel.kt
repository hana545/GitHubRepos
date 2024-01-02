package com.example.githubrepos.ui.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.model.Repository
import com.example.githubrepos.networking.ApiModule
import kotlinx.coroutines.launch

class RepositoriesViewModel() : ViewModel() {

    private val _repoList = MutableLiveData<MutableList<Repository>>()
    val repoList: LiveData<MutableList<Repository>> get() = _repoList

    init {
        _repoList.value = mutableListOf()
        getRepositories("a")
    }

    /*
    private fun getRepositories(){
        _repoList.value = arrayListOf(
            Repository("501","Harshita","Harshita@gmail.com", "blablabla", "", "", 5,8,1, arrayListOf("C", "Python")),
            Repository("502","minu","minu@gmail.com","blablabla","", "", 6,8,4, arrayListOf("C++")),
            Repository("503","ram","ram@gmail.com","blablabla","", "", 1,8,6, arrayListOf("Ruby")),
            Repository("504","sham","sham@gmail.com","blablabla","", "", 5,3,1, arrayListOf("Java", "Kotlin", "Javascript")),
            Repository("505","raja","raja@gmail.com","blablabla","", "", 1,1,8, arrayListOf("C#")),
            Repository("506","harsh","harsh@gmail.com","blablabla","", "", 7,1,10, arrayListOf("React", "C#")),
            Repository("507","harshu","harshu@gmail.com","blablabla","", "", 0,8,6, arrayListOf("PHP", "Angular")),
            Repository("508","xyz","xyz@gmail.com","blablabla","", "", 3,2,0, arrayListOf("Python")),
            Repository("509","abc","abc@gmail.com","blablabla","", "", 8,0,0, arrayListOf("Assembly")),
            Repository("510","pqr","pqr@gmail.com","blablabla","", "", 4,6,1, arrayListOf("C")),
        )
    }*/
    fun getRepositories(query: String) {
        viewModelScope.launch {
            try {
                listRepositories(query)
            } catch (ups: Exception) {
                Log.e("REPOLIST", "ups " + ups.toString())
            }
        }


    }

    private suspend fun listRepositories(query: String) {
        val response = ApiModule.retrofit.searchRepositories(query)
        if (response.isSuccessful) {
            Log.i("REPOLIST", "response ${response.body()}")
            _repoList.value = response.body()?.repos
        }
    }

}