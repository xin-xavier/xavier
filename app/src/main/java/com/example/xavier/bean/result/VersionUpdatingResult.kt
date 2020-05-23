package com.example.prepotency.bean.result

class VersionUpdatingResult {
    /**
     * version : 2.0.1
     * content : 更新福利
     * enforce : 1
     * code : 11
     */
    private var version: String = ""
    private var content: String = ""
    private var enforce = 0
    private var code = 0

    fun getVersion(): String {
        return version
    }

    fun setVersion(version: String) {
        this.version = version
    }

    fun getContent(): String {
        return content
    }

    fun setContent(content: String) {
        this.content = content
    }

    fun getEnforce(): Int {
        return enforce
    }

    fun setEnforce(enforce: Int) {
        this.enforce = enforce
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }
}