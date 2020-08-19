package com.heptogontest.shrihari.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * @param imageUrl
 * @param id
 * @param value
 * @param selected
 */
data class Value(
    @SerializedName("selected") val selected: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("value") val value: String? = null,
    @SerializedName("imageUrl") val imageUrl:Any?) {
}