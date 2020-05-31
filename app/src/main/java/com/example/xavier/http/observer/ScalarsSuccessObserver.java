package com.example.xavier.http.observer;

import com.blankj.utilcode.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ScalarsSuccessObserver implements Observer<String> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String message;
        if (e instanceof SocketTimeoutException){
            message="网络连接超时！";
        }else if (e instanceof ConnectException){
            message="网络无法连接！";
        } else if (e instanceof HttpException) {
            message="网络中断，请检查您的网络状态！";
        } else if (e instanceof UnknownHostException) {
            message="网络错误，请检查您的网络状态！";
        }else {
            message = e.getMessage();
        }
        ToastUtils.showShort(message);
    }

    @Override
    public void onComplete() {

    }
}
