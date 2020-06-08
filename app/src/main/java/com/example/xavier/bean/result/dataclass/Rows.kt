package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class Rows(@SerializedName("start_selling")
                val startSelling: Int = 0,
                @SerializedName("after_service")
                val afterService: Int = 0,
                @SerializedName("num")
                val num: Int = 0,
                @SerializedName("is_astrict_num")
                val isAstrictNum: Int = 0,
                @SerializedName("video")
                val video: String = "",
                @SerializedName("type")
                val type: Int = 0,
                @SerializedName("content")
                val content: String = "",
                @SerializedName("sales")
                val sales: Int = 0,
                @SerializedName("platform")
                val platform: Int = 0,
                @SerializedName("xn_cateid")
                val xnCateid: Int = 0,
                @SerializedName("video_image")
                val videoImage: String = "",
                @SerializedName("goods_brand_id")
                val goodsBrandId: Int = 0,
                @SerializedName("price")
                val price: String = "",
                @SerializedName("shipping_method")
                val shippingMethod: Int = 0,
                @SerializedName("genre")
                val genre: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("goods_type_id")
                val goodsTypeId: Int = 0,
                @SerializedName("attr")
                val attr: Attr,
                @SerializedName("purchase_type")
                val purchaseType: Int = 0,
                @SerializedName("image")
                val image: String = "",
                @SerializedName("delivery")
                val delivery: String = "",
                @SerializedName("images")
                val images: List<String>?,
                @SerializedName("createtime")
                val createtime: Int = 0,
                @SerializedName("committed")
                val committed: List<CommittedItem>?,
                @SerializedName("courier_fee")
                val courierFee: Int = 0,
                @SerializedName("purchase")
                val purchase: Int = 0,
                @SerializedName("goods_id")
                val goodsId: Int = 0,
                @SerializedName("brand_name")
                val brandName: String = "",
                @SerializedName("is_astrict")
                val isAstrict: Int = 0,
                @SerializedName("templateid")
                val templateid: Int = 0,
                @SerializedName("cate_attr_text")
                val cateAttrText: List<CateAttrTextItem>?,
                @SerializedName("praise")
                val praise: Int = 0,
                @SerializedName("canBuy")
                val canBuy: Int = 0,
                @SerializedName("shop_id")
                val shopId: Int = 0,
                @SerializedName("shiptime")
                val shiptime: String = "",
                @SerializedName("is_putaway")
                val isPutaway: Int = 0,
                @SerializedName("shopname")
                val shopname: String = "",
                @SerializedName("logistics_weight")
                val logisticsWeight: String = "",
                @SerializedName("name")
                val name: String = "")