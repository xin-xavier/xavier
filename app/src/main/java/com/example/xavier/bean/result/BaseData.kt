package com.example.xavier.bean.result

class BaseData<T> {
    var code = 0
    var result: T? = null
    var msg = ""
    override fun toString(): String {
        return "BaseData(code=$code, result=$result, msg='$msg')"
    }
}