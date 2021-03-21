package upday.droidconmvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import upday.droidconmvvm.databinding.ActivityMainBinding
import upday.droidconmvvm.model.Language

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as DroidconApplication).dataModel
        )
    }

    private lateinit var mLanguageSpinnerAdapter: LanguageSpinnerAdapter

    private lateinit var _activityView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityView.root)
        setupObservers()
    }

    private fun setupObservers() {

        setLanguages(mViewModel.supportedLanguages)

        val selectedLanguageObserver = Observer<String> { greeting ->
            setGreeting(greeting)
        }
        mViewModel.currentGreeting.observe(this, selectedLanguageObserver)

        _activityView.languagesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                itemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing to do here
                Log.d(this.javaClass.name, "cayo aqui")
            }
        }
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
    }

    private fun itemSelected(position: Int) {
        val languageSelected = mLanguageSpinnerAdapter.getItem(position)
        mViewModel.setLanguageSelected(languageSelected!!)
    }
}