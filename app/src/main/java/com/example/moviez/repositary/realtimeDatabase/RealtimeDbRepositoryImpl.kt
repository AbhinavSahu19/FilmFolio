package com.example.moviez.repositary.realtimeDatabase

import android.util.Log
import androidx.compose.animation.core.snap
import com.example.moviez.utils.ResponseModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDbRepositoryImpl @Inject constructor(
    private val dbRef: DatabaseReference
): RealtimeDbRepository{
    override fun getData(userId: String): Flow<ResponseModel<List<StorageModel>>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val valueListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val returnModel = mutableListOf<StorageModel>()

                snapshot.children.forEach{listSnapshot->
                    val list = mutableListOf<StorageItem>()
                    listSnapshot.child("list").children.forEach {itemSnapshot->
                        val item = itemSnapshot.getValue(StorageItem::class.java)
                        if(item != null){
                            list.add(item)
                        }
                    }
                    val listName = listSnapshot.child("name").getValue(String::class.java)?:""
                    returnModel.add(StorageModel(listName, list))
                }
                trySend(ResponseModel.Success(returnModel))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResponseModel.Error(error.message))
            }

        }

        dbRef.child(userId).child("data").addValueEventListener(valueListener)

        awaitClose{
            dbRef.removeEventListener(valueListener)
        }
    }

    override fun deleteData(userId: String): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

            dbRef.child(userId).removeValue().addOnSuccessListener {
                trySend(ResponseModel.Success("Data Deleted Successfully"))
            }
                .addOnFailureListener {
                    trySend(ResponseModel.Error(it.message ?: "Unknown Error"))
                }

        awaitClose{ cancel()}
    }

    override fun getListNames(userId: String): Flow<ResponseModel<List<String>>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listNames = mutableListOf<String>()
                snapshot.children.forEach { listSnapshot ->
                    val listName = listSnapshot.child("name").getValue(String::class.java)
                    if (listName != null) {
                        listNames.add(listName)
                    }
                }
                trySend(ResponseModel.Success(listNames))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResponseModel.Error(error.message))
            }
        }

        dbRef.child(userId).child("data").addValueEventListener(listener)
        awaitClose{dbRef.removeEventListener(listener)}
    }

    override fun getDataWithName(
        userId: String,
        listName: String
    ): Flow<ResponseModel<StorageModel>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val valueListener = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<StorageItem>()
                snapshot.child("list").children.forEach { listSnapshot->
                    val listItem = listSnapshot.getValue(StorageItem::class.java)
                    if (listItem != null) {
                        list.add(listItem)
                    }
                }
                val name = snapshot.child("name").getValue(String::class.java)?:""
                trySend(ResponseModel.Success(StorageModel(name, list)))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResponseModel.Error(error.message))
            }

        }

        dbRef.child(userId).child("data").child(listName).addValueEventListener(valueListener)
        awaitClose { dbRef.child(userId).child("data").child(listName).removeEventListener(valueListener) }
    }

    override fun containItem(
        userId: String,
        listName: String,
        item: StorageItem
    ): Flow<ResponseModel<Boolean>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val listRef = dbRef.child(userId).child("data").child(listName).child("list")
        listRef.get().addOnSuccessListener { snapshot ->
            val itemList = mutableListOf<StorageItem>()
            snapshot.children.forEach { itemSnapshot ->
                val storageItem = itemSnapshot.getValue(StorageItem::class.java)
                if (storageItem != null) {
                    itemList.add(storageItem)
                }
            }
            val returnValue = itemList.contains(item)
            trySend(ResponseModel.Success(returnValue))
        }.addOnFailureListener { exception ->
            trySend(ResponseModel.Error(exception.message ?: "Unknown Error"))
        }

        awaitClose { cancel() }
    }

    override fun addList(userId: String, listName: String): Flow<ResponseModel<String>> = callbackFlow {
        trySend(ResponseModel.Loading)

        val dataItem = StorageModel(listName)
        val listRef = dbRef.child(userId).child("data")
            .child(listName)

        listRef.get().addOnSuccessListener{snapshot->
            if(snapshot.exists()){
                trySend(ResponseModel.Error("List Already Exist"))
            }
            else{
                listRef.setValue(dataItem).addOnSuccessListener { trySend(ResponseModel.Success("Success")) }
                    .addOnFailureListener{trySend(ResponseModel.Error(it.message ?: "Unknown Error"))}
            }
        }
            .addOnFailureListener{it.message ?: "Unknown Error"}
        awaitClose{ cancel()}

    }

    override fun addItem(
        userId: String,
        listName: String,
        item: StorageItem
    ): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val listRef = dbRef.child(userId).child("data").child(listName).child("list")
        listRef.get().addOnSuccessListener{snapshot ->

            val list = snapshot.getValue(object : GenericTypeIndicator<List<StorageItem>>() {})
                ?: emptyList()
            val updatedList = list + item
            listRef.setValue(updatedList).addOnCompleteListener{task ->
                if(task.isSuccessful)trySend(ResponseModel.Success("Success"))
                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
            }
                .addOnFailureListener{ trySend(ResponseModel.Error(it.message ?: "Unknown Error")) }
        }
            .addOnFailureListener{ trySend(ResponseModel.Error(it.message  ?: "Unknown Error"))}

        awaitClose{ cancel()}
    }

    override fun deleteList(userId: String, listName: String): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

        dbRef.child(userId).child("data").child(listName).removeValue()
            .addOnCompleteListener { task->
                if(task.isSuccessful)trySend(ResponseModel.Success("Success"))
                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
            }
            .addOnFailureListener { trySend(ResponseModel.Error(it.message ?: "Unknown Error")) }

        awaitClose { cancel() }
    }

    override fun deleteItem(
        userId: String,
        listName: String,
        item: StorageItem
    ): Flow<ResponseModel<String>> = callbackFlow {
        trySend(ResponseModel.Loading)

        val listRef = dbRef.child(userId).child("data").child(listName).child("list")
        listRef.get().addOnSuccessListener { snapShot ->
            val list = snapShot.getValue(object : GenericTypeIndicator<List<StorageItem>>() {})
            val updatedList = list?.minus(item)
            listRef.setValue(updatedList).addOnCompleteListener { task ->
                if (task.isSuccessful) trySend(ResponseModel.Success("Success"))
                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
            }
                .addOnFailureListener {
                    trySend(
                        ResponseModel.Error(
                            it.message ?: "Unknown Error"
                        )
                    )
                }
        }
            .addOnFailureListener { trySend(ResponseModel.Error(it.message ?: "Unknown Error")) }

        awaitClose { cancel() }
    }

    override fun getKeywords(userId: String): Flow<ResponseModel<List<String>>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val valueListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val returnModel = mutableListOf<String>()

                snapshot.children.forEach {keywordSnapshot->
                    val keyword = keywordSnapshot.getValue(String::class.java)
                    if(keyword != null){
                        returnModel.add(keyword)
                    }
                }
                trySend(ResponseModel.Success(returnModel))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResponseModel.Error(error.message))
            }
        }

        dbRef.child(userId).child("keyword").child("list").addValueEventListener(valueListener)
        awaitClose{
            dbRef.child(userId).child("keyword").child("list").removeEventListener(valueListener)
        }
    }
    override fun addKeyword(userId: String, keyword: String): Flow<ResponseModel<String>> = callbackFlow {
        trySend(ResponseModel.Loading).isSuccess

        val listRef = dbRef.child(userId).child("keyword").child("list")
        listRef.get().addOnSuccessListener { snapshot ->
            //these two lines are special
            val list = snapshot.getValue(object : GenericTypeIndicator<MutableList<String>>() {})
            val updatedList = list?.apply { add(keyword) } ?: mutableListOf(keyword)

            listRef.setValue(updatedList).addOnCompleteListener { task ->
                if (task.isSuccessful) trySend(ResponseModel.Success("Success")).isSuccess
                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error")).isSuccess
            }.addOnFailureListener {
                trySend(ResponseModel.Error(it.message ?: "Unknown Error")).isSuccess
            }
        }.addOnFailureListener {
            trySend(ResponseModel.Error(it.message ?: "Unknown Error")).isSuccess
        }

        awaitClose { cancel() }
    }

//    override fun addKeyword(userId: String, keyword: String): Flow<ResponseModel<String>> = callbackFlow{
//        trySend(ResponseModel.Loading)
//
//        val listRef = dbRef.child(userId).child("keyword").child("list")
//        listRef.get().addOnSuccessListener { snapshot->
//            val list = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {})
//            val updatedList = list?.plus(keyword)
//
//            listRef.setValue(updatedList).addOnCompleteListener { task ->
//                if (task.isSuccessful) trySend(ResponseModel.Success("Success"))
//                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
//            }
//                .addOnFailureListener {
//                    trySend(
//                        ResponseModel.Error(
//                            it.message ?: "Unknown Error"
//                        )
//                    )
//                }
//        }
//            .addOnFailureListener {
//                trySend(ResponseModel.Error(it.message ?: "Unknown Error"))
//            }
//
//        awaitClose{ cancel()}
//    }

    override fun deleteKeyword(userId: String, keyword: String): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

        val listRef = dbRef.child(userId).child("keyword").child("list")
        listRef.get().addOnSuccessListener { snapshot->
            val list = snapshot.getValue(object : GenericTypeIndicator<List<String>>() {})
            val updatedList = list?.minus(keyword)

            listRef.setValue(updatedList).addOnCompleteListener { task ->
                if (task.isSuccessful) trySend(ResponseModel.Success("Success"))
                else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
            }
                .addOnFailureListener {
                    trySend(
                        ResponseModel.Error(
                            it.message ?: "Unknown Error"
                        )
                    )
                }
        }
            .addOnFailureListener {
                trySend(ResponseModel.Error(it.message ?: "Unknown Error"))
            }

        awaitClose{ cancel()}
    }

}