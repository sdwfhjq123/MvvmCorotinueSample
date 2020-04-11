package com.yinhao.commonmodule.base.repository.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * author:  SHIGUANG
 * date:    2019/4/18
 * version: v1.0
 * ### description: 基础Dao,提供 insert update delete 的基本方法
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(vararg beans: T)

    @Update
    fun update(vararg beans: T): Int

    @Delete
    fun delete(vararg beans: T): Int
}
