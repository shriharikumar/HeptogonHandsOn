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
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("mandatory") val mandatory: Int?,
    @SerializedName("placeholder") val placeholder: String?,
    @SerializedName("multiOptionFlag") val multiOptionFlag: Int?,
    @SerializedName("question") val question: String?,
    @SerializedName("answers") val answers: String?,
    @SerializedName("imageFlag") val imageFlag: Int?,
    @SerializedName("values") val values: List<Value>?,
    @SerializedName("multiSelectFlag") val multiSelectFlag: Int?,
    @SerializedName("images") val images: List<Any>?,
    @SerializedName("type") val type: String?,
    @SerializedName("questionId") val questionId: Int?,
    @SerializedName("url") val url: String?) {

}