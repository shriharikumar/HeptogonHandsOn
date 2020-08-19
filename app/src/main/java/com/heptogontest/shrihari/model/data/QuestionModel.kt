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
    @Expose @SerializedName("status") val  status:Boolean?,
    @Expose @SerializedName("courseProcessId") val courseProcessId:Int?,
    @Expose  @SerializedName("questions") val questions: ArrayList<Question>?) {

    }