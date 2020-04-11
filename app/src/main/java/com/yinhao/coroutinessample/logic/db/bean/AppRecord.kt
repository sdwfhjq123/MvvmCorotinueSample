package com.yinhao.coroutinessample.logic.db.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author:  SHIGUANG
 * date:    2018/8/28
 * version: v1.0
 * ### description: 通用程序记录
 */
@Entity(tableName = "app_record")
data class AppRecord
constructor(@ColumnInfo(name = "sign_name") var signName: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "user_id")
    var userId: String? = null

    @ColumnInfo(name = "certificate")
    var certificate: String? = null

    @ColumnInfo(name = "last_sign_time")
    var lastSignTime: Long? = 0


    constructor() : this("")
}
