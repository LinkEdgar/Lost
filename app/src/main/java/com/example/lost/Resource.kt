package com.example.lost

class Resource<T>(var status: Status = Status.LOADING, var data: T? = null){

    enum class Status{ ERROR, LOADING , SUCCESS}
}