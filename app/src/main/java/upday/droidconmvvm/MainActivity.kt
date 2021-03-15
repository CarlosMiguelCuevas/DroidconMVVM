package upday.droidconmvvm

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import upday.droidconmvvm.databinding.ActivityMainBinding
import upday.droidconmvvm.model.Language

class MainActivity : AppCompatActivity() {
    private lateinit var mCompositeDisposable: CompositeDisposable

    private val mViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as DroidconApplication).dataModel,
            (application as DroidconApplication).schedulerProvider
        )
    }

    //    private lateinit var mViewModel: MainViewModel
    private var mLanguageSpinnerAdapter: LanguageSpinnerAdapter? = null

    private lateinit var _activityView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityView.root)
        setupViews()
    }

    private fun setupViews() {
        _activityView.languagesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {
                itemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing to do here
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unBind()
    }

    private fun bind() {
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable.add(mViewModel.greeting
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { greeting: String -> setGreeting(greeting) })
        mCompositeDisposable.add(mViewModel.supportedLanguages
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { languages: List<Language> -> setLanguages(languages) })
    }

    private fun unBind() {
        mCompositeDisposable.clear()
    }

    private fun setGreeting(greeting: String) {
        _activityView.greetingTextView.text = greeting
    }

    private fun setLanguages(languages: List<Language>) {
        mLanguageSpinnerAdapter = LanguageSpinnerAdapter(
            this,
            R.layout.language_item,
            languages
        )
        _activityView.languagesSpinner.adapter = mLanguageSpinnerAdapter
        _activityView.languagesSpinner.setSelection(mLanguageSpinnerAdapter!!.getPosition(mViewModel.selectedLanguage))
    }

    private fun itemSelected(position: Int) {
        assert(mLanguageSpinnerAdapter != null)
        val languageSelected = mLanguageSpinnerAdapter!!.getItem(position)
        mViewModel.languageSelected(languageSelected!!)
    }
}