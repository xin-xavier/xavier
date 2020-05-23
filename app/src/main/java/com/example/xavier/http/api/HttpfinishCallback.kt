package com.example.xavier.http.api

import io.reactivex.disposables.Disposable

interface HttpfinishCallback<T> {

    fun disposable(d: Disposable)
    fun onSuccess(t:T)
    fun onError(errorMsg:String)

}