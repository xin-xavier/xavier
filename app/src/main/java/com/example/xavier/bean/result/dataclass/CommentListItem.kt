package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class CommentListItem(@SerializedName("oid")
                           val oid: Int = 0,
                           @SerializedName("video")
                           val video: String = "",
                           @SerializedName("append_content")
                           val appendContent: String = "",
                           @SerializedName("content")
                           val content: String = "",
                           @SerializedName("is_reply")
                           val isReply: Int = 0,
                           @SerializedName("uid")
                           val uid: Int = 0,
                           @SerializedName("score")
                           val score: Int = 0,
                           @SerializedName("price")
                           val price: String = "",
                           @SerializedName("baby_score")
                           val babyScore: Int = 0,
                           @SerializedName("speed_score")
                           val speedScore: Int = 0,
                           @SerializedName("id")
                           val id: Int = 0,
                           @SerializedName("reply")
                           val reply: String = "",
                           @SerializedName("sku")
                           val sku: String = "",
                           @SerializedName("applaud")
                           val applaud: Int = 0,
                           @SerializedName("order")
                           val order: String = "",
                           @SerializedName("goods_name")
                           val goodsName: String = "",
                           @SerializedName("images")
                           val images: String = "",
                           @SerializedName("createtime")
                           val createtime: Int = 0,
                           @SerializedName("logistics_score")
                           val logisticsScore: Int = 0,
                           @SerializedName("goods_id")
                           val goodsId: Int = 0,
                           @SerializedName("u_avatar")
                           val uAvatar: String = "",
                           @SerializedName("append_images")
                           val appendImages: String = "",
                           @SerializedName("shop_id")
                           val shopId: Int = 0,
                           @SerializedName("sku_condition")
                           val skuCondition: String = "",
                           @SerializedName("service_score")
                           val serviceScore: Int = 0,
                           @SerializedName("attributes")
                           val attributes: String = "",
                           @SerializedName("u_nickna me")
                           val uNicknaMe: String = "",
                           @SerializedName("pageview")
                           val pageview: Int = 0,
                           @SerializedName("status")
                           val status: Int = 0)