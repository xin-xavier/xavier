package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class Comment(@SerializedName("comment_list")
                   val commentList: List<CommentListItem>?,
                   @SerializedName("count")
                   val count: Int = 0)