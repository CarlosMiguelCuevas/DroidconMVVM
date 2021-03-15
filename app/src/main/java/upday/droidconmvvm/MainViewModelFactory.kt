package upday.droidconmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import upday.droidconmvvm.datamodel.IDataModel
import upday.droidconmvvm.schedulers.ISchedulerProvider

class MainViewModelFactory(val dataModel: IDataModel, val schedulerProvider: ISchedulerProvider): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataModel,schedulerProvider) as T
    }

}