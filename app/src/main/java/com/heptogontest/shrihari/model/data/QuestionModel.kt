package com.heptogontest.shrihari.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * @param courseProcessId
 * @param questions
 * @param status
 */
data class QuestionModel(val  status:Boolean?,val courseProcessId:Int?,val questions: List<Question>?) {


    }