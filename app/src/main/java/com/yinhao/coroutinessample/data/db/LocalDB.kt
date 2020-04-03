package com.yinhao.coroutinessample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import team.fcma.xframepro2.repository.db.bean.AppRecord
import team.fcma.xframepro2.repository.db.dao.AppRecordDao

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 本地数据库
 */
@Database(
    version = 1,
    entities = [AppRecord::class]
)
abstract class LocalDB : RoomDatabase() {

    /**
     * ### AppRecord的DAO
     */
    abstract fun appRecordDao(): AppRecordDao

}
