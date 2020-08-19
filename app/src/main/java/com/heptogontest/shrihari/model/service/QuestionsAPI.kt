package com.heptogontest.shrihari.model.service

import com.heptogontest.shrihari.model.data.QuestionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface QuestionsAPI {
    @Headers("Content-Type: application/json")
    @GET("get/cpOAeecWGa?indent=2")
    fun getQuestions(): Call<QuestionModel?>
}