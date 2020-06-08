package com.example.xavier.utils

import android.util.Log

object XavierLogUtils {

    fun longInfo(tag: String, msg: String) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        var message = msg
        val max_str_length = 2001 - tag.length
        //大于4000时
        while (message.length > max_str_length) {
            Log.i(tag, message.substring(0, max_str_length))
            message = message.substring(max_str_length)
        }
        //剩余部分
        Log.i(tag, message)
    }

    fun jsonInfo(tag: String, msg: String) {
        if(JsonUtils.getInstance().validate(msg)){
            longInfo(tag,msg)
        }
    }

}