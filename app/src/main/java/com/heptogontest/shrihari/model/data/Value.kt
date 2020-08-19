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
    @Expose @SerializedName("selected") val selected: Int? = null,
    @Expose @SerializedName("id") val id: Int? = null,
    @Expose  @SerializedName("value") val value: String? = null,
    @Expose @SerializedName("imageUrl") val imageUrl:Any?) {
}