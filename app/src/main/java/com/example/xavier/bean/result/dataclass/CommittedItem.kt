package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class CommittedItem(@SerializedName("ensure_name")
                         val ensureName: String = "",
                         @SerializedName("ensure_introduce")
                         val ensureIntroduce: String = "")