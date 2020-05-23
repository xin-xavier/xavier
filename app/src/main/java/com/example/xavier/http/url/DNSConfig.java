package com.example.xavier.http.url;

public class DNSConfig {

    private static volatile DNSConfig singleton;

    private DNSConfig() {
    }

    public static DNSConfig getInstance() {
        if (singleton == null) {
            synchronized (DNSConfig.class) {
                if (singleton == null) {
                    singleton = new DNSConfig();
                }
            }
        }
        return singleton;
    }

    /**
     * @method 获取域名服务器
     * boolean startsWith(String prefix)
     * 测试此字符串是否以指定的前缀开始。
     * @param url
     * @return DNS(domain name server)
     */
    public String getDNS(String url){
        if(url.startsWith(ConstantUrl.V)){
            return getApiUrl();
        } else if(url.startsWith(ConstantUrl.VVV)){
            return getApiServerUrl();
        }else{
            return getLiveApiUrl();
        }
    }

    /**
     * dns调试
     * @return boolean
     */
    public boolean dnsDEBUG(){
        return ConstantUrl.DEBUG;
    }

    public String getApiUrl(){
        return dnsDEBUG() ? ConstantUrl.BASE_URL_DEBUG : ConstantUrl.BASE_URL_RELEASE;
    }

    public String getApiServerUrl(){
        return dnsDEBUG() ? ConstantUrl.BASE_URL_SERVER_DEBUG : ConstantUrl.BASE_URL_SERVER_RELEASE;
    }

    public String getLiveApiUrl(){
        return dnsDEBUG() ? ConstantUrl.BASE_URL_LIVE_DEBUG : ConstantUrl.BASE_URL_LIVE_RELEASE;
    }

    public String getH5Url(){
        return dnsDEBUG() ? ConstantUrl.H5_URL_DEBUG : ConstantUrl.H5_URL_RELEASE;
    }

    public String getWs(){
        return dnsDEBUG() ? ConstantUrl.WS_DEBUG : ConstantUrl.WS_RELEASE;
    }

}