package com.heptogontest.shrihari.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * @param courseProcessId
 * @param questions
 * @param status
 */
data class QuestionModel(
    @SerializedName("status") val  status:Boolean?,
    @SerializedName("courseProcessId") val courseProcessId:Int?,
    @SerializedName("courseProcessId") val questions: List<Question>?) {

    }