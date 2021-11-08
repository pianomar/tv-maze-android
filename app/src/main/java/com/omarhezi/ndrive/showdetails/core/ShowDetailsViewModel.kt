package com.omarhezi.ndrive.showdetails.core

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.showslist.api.ShowsRepository
import com.omarhezi.ndrive.showslist.core.modules.ShowViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val repository: ShowsRepository
) : ViewModel() {

    private val _showAliasesStatus: MutableLiveData<ShowsAliasesStatus> = MutableLiveData()
    val showAliasesStatus: LiveData<ShowsAliasesStatus> = _showAliasesStatus

    fun getShowAliases(id: Int) {
        repository.getShowAliasesById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _showAliasesStatus.value = ShowsAliasesStatus.Success(it.joinToString())
            }, {
                _showAliasesStatus.value = ShowsAliasesStatus.Error(R.string.aliases_error)
            })
    }

    sealed class ShowsAliasesStatus {
        data class Success(val data: String) : ShowsAliasesStatus()
        data class Error(@StringRes val message: Int) : ShowsAliasesStatus()
    }
}