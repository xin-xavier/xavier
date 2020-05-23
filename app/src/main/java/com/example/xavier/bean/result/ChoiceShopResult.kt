package com.example.prepotency.bean.result

class ChoiceShopResult {
    /**
     * id : 10
     * headImage : https://www.gravatar.com/avatar/2e8a175e3471e86ae115ab5465c02596?s=120&d=identicon
     * store_name : 虚拟充值平台
     * business_category : 1
     */
    var id = 0
    var headImage: String? = null
    var store_name: String? = null
    var business_category = 0

    override fun toString(): String {
        return "ChoiceShopResult{" +
                "id=" + id +
                ", headImage='" + headImage + '\'' +
                ", store_name='" + store_name + '\'' +
                ", business_category=" + business_category +
                '}'
    }
}