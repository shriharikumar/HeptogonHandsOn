package com.heptogontest.shrihari.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heptogontest.shrihari.model.data.QuestionModel
import com.heptogontest.shrihari.model.service.QuestionService

 class QuestionsViewModel: ViewModel() {


     companion object {
         val questionMutableList: MutableLiveData<QuestionModel> by lazy {
             MutableLiveData<QuestionModel>()
         }

         fun getQuestionFromService() {
             QuestionService.getQuestions()
         }
     }

     fun getStaticInstance(): MutableLiveData<QuestionModel> {
         return questionMutableList;
     }

}