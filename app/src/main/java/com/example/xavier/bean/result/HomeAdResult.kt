package com.example.prepotency.bean.result

class HomeAdResult {
    /**
     * id : 19
     * position : 0
     * gid : 115
     * url :
     * image : http://admin.chengmeiyoupin.com/uploads/20191122/f9ea078bd77c291d3c6a27453dfa86e9.png
     * mid : 50
     * shipping_method : 0
     */
    var id = 0
    var position = 0
    var gid = 0
    var url: String? = null
    var image: String = ""
    var mid = 0
    var shipping_method = 0

    override fun toString(): String {
        return "HomeAdResult{" +
                "id=" + id +
                ", position=" + position +
                ", gid=" + gid +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", mid=" + mid +
                ", shipping_method=" + shipping_method +
                '}'
    }
}