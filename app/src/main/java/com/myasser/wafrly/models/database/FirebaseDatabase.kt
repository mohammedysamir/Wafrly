package com.myasser.wafrly.models.database

abstract class FirebaseDatabase: Database {
    companion object:Database {
        override fun getDatabase(): Database {
            TODO("implement singleton")
        }
    }
}