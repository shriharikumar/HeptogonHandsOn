package com.heptogontest.shrihari

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.viewmodel.QuestionsViewModel
import io.reactivex.disposables.Disposable

var disposable: Disposable? = null



class MainActivity : AppCompatActivity()  {

    val TAG = MainActivity::class.java.simpleName
    private val questionsModel: QuestionsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call to service method which calls the API to get data
        QuestionsViewModel.getQuestionFromService();


        //Registering Observer for catching data on UI Thread
        val questionObserver = Observer<QuestionModel> { data ->
            // Update the UI, in this case, a TextView.
                Log.d(TAG,"Data Received on UI Thread")
                Log.d(TAG,data.toString())
        }

        //Registering the observe for the current life cycle owner
        questionsModel.getStaticInstance().observe(this@MainActivity, questionObserver)

    }


}

