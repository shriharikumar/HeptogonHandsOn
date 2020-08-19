package com.heptogontest.shrihari.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 *
 * @param images
 * @param questionId
 * @param question
 * @param imageFlag
 * @param values
 * @param errorMessage
 * @param answers
 * @param type
 * @param mandatory
 * @param url
 * @param multiSelectFlag
 * @param placeholder
 * @param multiOptionFlag
 */


data class Question(
    val errorMessage: String?,
    val mandatory: Int?,
    val placeholder: String?,
    val multiOptionFlag: Int?,
    val question: String?,
    val answers: String?,
    val imageFlag: Int?,
    val values: List<Value>?,
    val multiSelectFlag: Int?,
    val images: List<Any>?,
    val type: String?,
    val questionId: Int?,
    val url: String?) {

}