package com.example.xavier.app

class AppManager{
    companion object {
        /**
         * 登录状态
         */
        fun isLogin(): Boolean {
            //return PrefUtils.getBoolean(Constants.LOGIN, false)
            return true
        }

        /**
         * 退出登录，重置用户状态
         */
        fun resetUser() {
            //发送退出登录消息
            /*EventBus.getDefault().post(LogoutEvent())
            PrefUtils.setBoolean(Constants.LOGIN, false)
            PrefUtils.removeKey(Constants.USER_INFO)*/
        }
    }
}