package com.example.xavier.bean.result

class VersionUpdatingResult {
    /**
     * version : 2.0.1
     * content : 更新福利
     * enforce : 1
     * code : 11
     */
    private var version: String = ""
    private var content: String = ""
    // 指定一个默认值
    private var enforce = -1
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