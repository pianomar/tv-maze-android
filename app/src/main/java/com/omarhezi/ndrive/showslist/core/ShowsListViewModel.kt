package com.omarhezi.ndrive.showslist.core

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.showslist.api.ShowsRepository
import com.omarhezi.ndrive.showslist.core.modules.ShowViewData
import com.omarhezi.ndrive.showslist.core.modules.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel @Inject constructor(
    private val repository: ShowsRepository
) : ViewModel() {

    private val _showsLiveData: MutableLiveData<ShowsListStatus> = MutableLiveData()
    val showsLiveData: LiveData<ShowsListStatus> = _showsLiveData

    fun getShowsListByQuery(query: String) {
        if (query.trim().isEmpty()) return

        repository.getShowsByQuery(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ shows ->
                if (shows.isEmpty()) {
                    setErrorMessage(R.string.not_found_error)
                } else {
                    _showsLiveData.value = ShowsListStatus.Success(shows.map { it.toViewData() })
                }
            }, {
                setErrorMessage(R.string.generic_error)
            })

    }

    private fun setErrorMessage(@StringRes messageResource: Int) {
        _showsLiveData.value = ShowsListStatus.Error(messageResource)
        _showsLiveData.value = ShowsListStatus.Error(null)
    }

    sealed class ShowsListStatus {
        data class Success(val data: List<ShowViewData>) : ShowsListStatus()
        data class Error(@StringRes val message: Int?) : ShowsListStatus()
    }
}
