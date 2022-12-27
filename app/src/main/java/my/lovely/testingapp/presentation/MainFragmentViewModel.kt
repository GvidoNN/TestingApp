package my.lovely.testingapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.lovely.testingapp.domain.models.CardModel

class MainFragmentViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<CardModel>()
}