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
data class Value( var selected: Int? = null,var id: Int? = null,var value: String? = null,val imageUrl:Any?) {
}