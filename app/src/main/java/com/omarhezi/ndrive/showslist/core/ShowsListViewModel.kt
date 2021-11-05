package com.omarhezi.ndrive.showslist.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omarhezi.ndrive.showslist.api.ShowsRepository
import com.omarhezi.ndrive.showslist.core.modules.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel @Inject constructor(
    private val repository: ShowsRepository
) : ViewModel() {

    private val _showsLiveData: MutableLiveData<List<Show>> = MutableLiveData()
    val showsLiveData: LiveData<List<Show>> = _showsLiveData

    fun getShowsListByQuery(query: String) {
        repository.getShowsByQuery(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _showsLiveData.value = it
            }, {
                Log.e("getShowsListByQuery", it.message.toString())
            })

    }
}