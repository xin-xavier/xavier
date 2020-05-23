package com.example.xavier.http.url

interface ConstantUrl {
    companion object {
        const val DEBUG = true
        // url 版本
        const val V = "v1/"
        const val VV = "v2/"
        const val VVV = "v3/"
        const val BASE_URL_DEBUG = "https://api.chengmeiyoupin.com/"
        const val BASE_URL_RELEASE = "https://api.chengmeiyouxuan.com/"
        const val BASE_URL_SERVER_DEBUG = "https://api.server.chengmeiyoupin.com/"
        const val BASE_URL_SERVER_RELEASE = "https://api.server.chengmeiyouxuan.com/"
        const val BASE_URL_LIVE_DEBUG = "https://live.chengmeiyoupin.com/"
        const val BASE_URL_LIVE_RELEASE = "https://live.chengmeiyouxuan.com/"

        // 连接
        const val WS_DEBUG = "ws://connector.chengmeiyoupin.com:9503"
        const val WS_RELEASE = "wss://chat.chengmeiyouxuan.com:"
        const val H5_URL_DEBUG = "https://h5.chengmeiyoupin.com/"
        const val H5_URL_RELEASE = "https://h5.chengmeiyouxuan.com/"
        const val QiNiuYun_DNS = "https://upload.chengmeiyouxuan.com/"
        // 测试 WS
        const val TEST_WS = "ws://echo.websocket.org"
        // 一个 test 视频
        const val TEST_VIDEO_PATH = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        // 一张 test 图片
        const val TEST_IMAGE_PATH =
            "http://b-ssl.duitang.com/uploads/item/201710/01/20171001004645_WSZye.thumb.700_0.jpeg"

        // 隐私政策
        var variousPrivacy = "v3/various/privacy"
        // 许可协议
        var variousProtocol = "v3/various/protocol"
    }
}