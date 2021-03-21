package upday.droidconmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import upday.droidconmvvm.datamodel.IDataModel

class MainViewModelFactory(val dataModel: IDataModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataModel) as T
    }

}