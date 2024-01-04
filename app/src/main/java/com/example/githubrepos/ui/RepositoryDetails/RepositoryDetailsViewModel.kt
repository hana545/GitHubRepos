package com.example.githubrepos.ui.RepositoryDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.model.Repository
import com.example.githubrepos.model.User
import com.example.githubrepos.networking.ApiModule
import kotlinx.coroutines.launch
import retrofit2.http.Url

class RepositoryDetailsViewModel() : ViewModel() {

    private val _repoLiveData = MutableLiveData<Repository>()
    val repoLiveData: LiveData<Repository> = _repoLiveData

    private val _languageList = MutableLiveData<Set<String>>()
    val languageList: LiveData<Set<String>> get() = _languageList

    private val _contributorsList = MutableLiveData<MutableList<User>>()
    val contributorsList: LiveData<MutableList<User>> get() = _contributorsList

    fun getRepository(user:String, name : String) {
        viewModelScope.launch {
            try {
                fetchRepository(user, name)
            } catch (ups: Exception) {
                Log.e("GETREPO", "ups " + ups.toString())
            }
        }


    }

    private suspend fun fetchRepository(user:String, name : String) {
        val response = ApiModule.retrofit.getRepository(user, name)
        if (response.isSuccessful) {
            Log.i("GETREPO", "response ${response.body()}")
            _repoLiveData.value = response.body()
            fetchLanguages(repoLiveData.value!!.languagesUrl)
            fetchContributors(repoLiveData.value!!.contributorsUrl)
        }
    }

    private suspend fun fetchLanguages(languagesUrl: String) {
        try {
            val response = ApiModule.retrofit.getLanguage(languagesUrl)
            Log.i("GETREPOLang", "response ${response.body()}")
            if (response.isSuccessful) {
                val languageMap: Map<String, Any> = response.body() ?: emptyMap()
                _languageList.value = languageMap.keys
            }
        } catch (ups: Exception) {
            Log.e("GETREPOLang", "ups " + ups.toString())
        }
    }

    private suspend fun fetchContributors(contributorsUrl: String) {
        try {
            val response = ApiModule.retrofit.getContributors(contributorsUrl)
            Log.i("GETREPOContributors", "response ${response.body()}")
            if (response.isSuccessful) {
                _contributorsList.value = response.body() as MutableList<User>?
            }
        } catch (ups: Exception) {
            Log.e("GETREPOContributors", "ups " + ups.toString())
        }
    }


}