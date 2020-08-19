package com.heptogontest.shrihari

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.heptogontest.shrihari.databinding.ActivityMainBinding
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.view.MainActivityListeners
import com.heptogontest.shrihari.viewmodel.QuestionsViewModel


class MainActivity : AppCompatActivity()  {

    val TAG = MainActivity::class.java.simpleName
    private val questionsModel: QuestionsViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout;


    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding view with activity
        binding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        (binding as ActivityMainBinding).swipeRefreshLayout.setOnRefreshListener {
            QuestionsViewModel.getQuestionFromService();
        }

        // Call to service method which calls the API to get data
        QuestionsViewModel.getQuestionFromService();


        //Registering Observer for catching data on UI Thread
        val questionObserver = Observer<QuestionModel> { data ->
            // Update the UI, in this case, a TextView.
                Log.d(TAG,"Data Received on UI Thread")
                Log.d(TAG,data.toString())
            Toast.makeText(this@MainActivity,"Content Loaded",Toast.LENGTH_SHORT).show();
            MainActivityListeners.Companion.isLoading = false;
            (binding as ActivityMainBinding).swipeRefreshLayout.isRefreshing = false;
        }

        //Registering the observe for the current life cycle owner
        questionsModel.getStaticInstance().observe(this@MainActivity, questionObserver)



    }


}

