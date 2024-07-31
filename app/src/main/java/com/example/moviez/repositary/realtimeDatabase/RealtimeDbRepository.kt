
package com.example.moviez.repositary.realtimeDatabase

import com.example.moviez.utils.ResponseModel
import kotlinx.coroutines.flow.Flow

interface RealtimeDbRepository {
    fun getData(userId: String) : Flow<ResponseModel<List<StorageModel>>>

    fun deleteData(userId: String): Flow<ResponseModel<String>>

    fun getListNames(userId: String): Flow<ResponseModel<List<String>>>

    fun getDataWithName(userId: String, listName: String): Flow<ResponseModel<StorageModel>>

    fun containItem(userId: String, listName: String, item: StorageItem): Flow<ResponseModel<Boolean>>

    fun addList(userId: String, listName: String): Flow<ResponseModel<String>>

    fun addItem(userId: String, listName: String, item: StorageItem): Flow<ResponseModel<String>>

    fun deleteList(userId: String, listName: String): Flow<ResponseModel<String>>

    fun deleteItem(userId: String, listName: String, item: StorageItem): Flow<ResponseModel<String>>

    fun getKeywords(userId: String): Flow<ResponseModel<List<String>>>

    fun addKeyword(userId: String, keyword: String): Flow<ResponseModel<String>>

    fun deleteKeyword(userId: String, keyword: String): Flow<ResponseModel<String>>
}