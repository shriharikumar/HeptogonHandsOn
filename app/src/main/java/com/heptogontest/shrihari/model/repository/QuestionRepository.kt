package com.heptogontest.shrihari.model.repository

import androidx.lifecycle.MutableLiveData
import com.heptogontest.shrihari.model.data.QuestionModel

class QuestionRepository {
    var questionMutableList: MutableLiveData<QuestionModel> = MutableLiveData()

}