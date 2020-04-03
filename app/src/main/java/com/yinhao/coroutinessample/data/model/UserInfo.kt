package com.yinhao.coroutinessample.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

@Entity(tableName = "user_info")
data class UserInfo
constructor(@SerializedName("id") var id: String) {

    @SerializedName("nick_name")
    var name: String? = null

    @SerializedName("current_token")
    var token: String? = null

    @SerializedName("header_img_url")
    var avatarUrl: String? = null

    @SerializedName("dpt_and_post")
    var dptAndPost: String? = null

    @SerializedName("gender")
    var gender: Int? = null

    @Expose
    var recordTime: Long? = null

    constructor() : this("")
}
