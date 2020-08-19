package com.heptogontest.shrihari.view

import com.heptogontest.shrihari.viewmodel.QuestionsViewModel

class MainActivityListeners {

    companion object {
        var isLoading: Boolean = true;
    }

    fun pullToRefresh()
    {
        println("called")
        QuestionsViewModel.getQuestionFromService();
    }
}