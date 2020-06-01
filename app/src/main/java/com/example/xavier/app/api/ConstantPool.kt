package com.example.xavier.app.api

import com.example.xavier.R

/**
 * App 常量池
 */
interface ConstantPool {
    companion object {
        const val APP_NAME = "Xavier"
        const val XAVIER = "Xavier"

        const val OK_TAG = "OKHttpClient: "
        const val OK_CACHE_STALE_SEC = 60 * 60 * 24 * 2.toLong() // 设缓存有效期为两天
        const val OK_CACHE_SIZE = 1024 * 1024 * 100 // 设缓存为100M
        const val OK_CACHE_DIR = "cacheDir" // 缓存路径
        const val CHCHE_CONTROL = "Cache-Control" // ok header
        const val PRAGMA = "Pragma" // ok header

        const val IDENTIFIER_NAME = "status_bar_height"
        const val DEF_TYPE = "dimen"
        const val ANDROID = "android"

        const val HOME = "首页"
        const val NEWS = "消息"
        const val SHOPPING_CART = "购物车"
        const val MINE = "我的"

        val TABS: Array<String> = arrayOf(HOME, NEWS, SHOPPING_CART, MINE)
        val ICON_SELECT_IDS: Array<Int> = arrayOf(
            R.drawable.tab_home_select,
            R.drawable.tab_news_select,
            R.drawable.tab_shopping_select,
            R.drawable.tab_mine_select
        )
        val ICON_UNSELECT_IDS: Array<Int> = arrayOf(
            R.drawable.tab_home_unselect,
            R.drawable.tab_news_unselect,
            R.drawable.tab_shopping_unselect,
            R.drawable.tab_mine_unselect
        )

        const val SOCKET_CLIENT_TAG = "WebSocketClient"
        const val PONG = "PONG"
        const val PING = "PING"
        const val SOCKET_BROADCAST_ACTION = "com.example.chatexample.MINGW_IM"
        const val MESSAGE = "message"
        const val PUSH_STATUS = "PushStatus"

        const val GRAY_SERVICE_ID = 9501
        const val SETTING_FOR_RESULTCODE = 100

        const val DUP_WIDTH = 300
        const val DUP_HEIGHT = 285
        const val TD_DIM_AMOUNT = 0.6f
        const val SIZE_MULTIPLIER = 0.6f
        const val DEFAULT_RADIUS = 4

        const val NULLCHARACTER = ""
        const val SPACE = " "
        const val YANG = "￥"
        const val UNDERLINE = "_"
        const val X = "x"
        const val COMMA = ","

        const val C_1 = "1"
        const val C_2 = "2"
        const val C_3 = "3"

        const val NOTIFICATION_SAMPLE = 0
        const val NOTIFICATION_ACTION = 1
        const val NOTIFICATION_REMOTE_INPUT = 2
        const val NOTIFICATION_BIG_PICTURE_STYLE = 3
        const val NOTIFICATION_BIG_TEXT_STYLE = 4
        const val NOTIFICATION_INBOX_STYLE = 5
        const val NOTIFICATION_MEDIA_STYLE = 6
        const val NOTIFICATION_MESSAGING_STYLE = 7
        const val NOTIFICATION_PROGRESS = 8
        const val NOTIFICATION_CUSTOM_HEADS_UP = 9
        const val NOTIFICATION_CUSTOM = 10

        const val ACTION_SIMPLE = "com.android.peter.notificationdemo.ACTION_SIMPLE"
        const val ACTION_ACTION = "com.android.peter.notificationdemo.ACTION_ACTION"
        const val ACTION_REMOTE_INPUT =
            "com.android.peter.notificationdemo.ACTION_REMOTE_INPUT"
        const val ACTION_BIG_PICTURE_STYLE =
            "com.android.peter.notificationdemo.ACTION_BIG_PICTURE_STYLE"
        const val ACTION_BIG_TEXT_STYLE =
            "com.android.peter.notificationdemo.ACTION_BIG_TEXT_STYLE"
        const val ACTION_INBOX_STYLE =
            "com.android.peter.notificationdemo.ACTION_INBOX_STYLE"
        const val ACTION_MEDIA_STYLE =
            "com.android.peter.notificationdemo.ACTION_MEDIA_STYLE"
        const val ACTION_MESSAGING_STYLE =
            "com.android.peter.notificationdemo.ACTION_MESSAGING_STYLE"
        const val ACTION_PROGRESS = "com.android.peter.notificationdemo.ACTION_PROGRESS"
        const val ACTION_CUSTOM_HEADS_UP_VIEW =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_HEADS_UP_VIEW"
        const val ACTION_CUSTOM_VIEW =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW"
        const val ACTION_CUSTOM_VIEW_OPTIONS_LOVE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LOVE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_PRE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PRE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_NEXT =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_NEXT"
        const val ACTION_CUSTOM_VIEW_OPTIONS_LYRICS =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LYRICS"
        const val ACTION_CUSTOM_VIEW_OPTIONS_CANCEL =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_CANCEL"

        const val ACTION_YES = "com.android.peter.notificationdemo.ACTION_YES"
        const val ACTION_NO = "com.android.peter.notificationdemo.ACTION_NO"
        const val ACTION_DELETE = "com.android.peter.notificationdemo.ACTION_DELETE"
        const val ACTION_REPLY = "com.android.peter.notificationdemo.ACTION_REPLY"
        const val REMOTE_INPUT_RESULT_KEY = "remote_input_content"

        const val EXTRA_OPTIONS = "options"
        const val MEDIA_STYLE_ACTION_DELETE = "action_delete"
        const val MEDIA_STYLE_ACTION_PLAY = "action_play"
        const val MEDIA_STYLE_ACTION_PAUSE = "action_pause"
        const val MEDIA_STYLE_ACTION_NEXT = "action_next"
        const val ACTION_ANSWER = "action_answer"
        const val ACTION_REJECT = "action_reject"
    }
}