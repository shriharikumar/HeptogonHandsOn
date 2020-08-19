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
    @Expose @SerializedName("errorMessage") val errorMessage: String?,
    @Expose @SerializedName("mandatory") val mandatory: Int?,
    @Expose @SerializedName("placeholder") val placeholder: String?,
    @Expose @SerializedName("multiOptionFlag") val multiOptionFlag: Int?,
    @Expose @SerializedName("question") val question: String?,
    @Expose @SerializedName("answers") var answers: String?,
    @Expose @SerializedName("imageFlag") val imageFlag: Int?,
    @Expose @SerializedName("values") val values: ArrayList<Value>?,
    @Expose @SerializedName("multiSelectFlag") val multiSelectFlag: Int?,
    @Expose  @SerializedName("images") val images: ArrayList<Any>?,
    @Expose @SerializedName("type") val type: String?,
    @Expose @SerializedName("questionId") val questionId: Int?,
    @Expose @SerializedName("url") val url: String?) {

}