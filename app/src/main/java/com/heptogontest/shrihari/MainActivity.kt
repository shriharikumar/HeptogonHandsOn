package com.heptogontest.shrihari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.model.service.NetworkInstance
import com.heptogontest.shrihari.model.service.QuestionService
import com.heptogontest.shrihari.model.service.QuestionsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
var disposable: Disposable? = null



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QuestionService.getQuestions(this@MainActivity)




    }


}

