package team.fcma.xframepro2.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import team.fcma.xframe.arch.repository.dao.BaseDao
import team.fcma.xframepro2.repository.db.bean.AppRecord

/**
 * author:      SHIGUANG
 * date:        2018/9/28
 * version:     v1.0
 * ### description: 为AppRecord提供的Dao
 */
@Dao
interface AppRecordDao : BaseDao<AppRecord> {

    /**
     * ### 获取全部
     */
    @Query("select * from app_record")
    suspend fun getAll(): List<AppRecord>

    /**
     * ### 根据用户ID [userId]查询记录列表
     */
    @Query("select * from app_record where user_id =:userId")
    suspend fun getListByUserId(userId: String): List<AppRecord>

    /**
     * ### 根据登录名[signName]查询记录列表
     */
    @Query("select * from app_record where sign_name =:signName")
    suspend fun getListBySignName(signName: String): List<AppRecord>

    /**
     * ### 根据登录名[signName]查询单个记录
     */
    @Query("select * from app_record where sign_name =:signName limit 0,1")
    suspend fun getBySignName(signName: String): AppRecord
}
