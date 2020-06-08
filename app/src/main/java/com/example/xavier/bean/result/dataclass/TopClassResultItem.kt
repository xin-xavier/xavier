package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class TopClassResultItem(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Int = 0)