package com.heptogontest.shrihari.model.service

import android.content.Context
import com.heptogontest.shrihari.model.data.QuestionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionService {

    companion object {
        fun getQuestions(context: Context) {
            val questionAPIService by lazy {
                NetworkInstance.getInstance(context)?.create(QuestionsAPI::class.java)
            }

            questionAPIService?.getQuestions()?.enqueue(object : Callback<QuestionModel?> {
                override fun onFailure(call: Call<QuestionModel?>, t: Throwable) {
                    println("failure = " + t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<QuestionModel?>,
                    response: Response<QuestionModel?>
                ) {
                    println("Sucess = " + response.body().toString())
                }


            })

        }
    }
}