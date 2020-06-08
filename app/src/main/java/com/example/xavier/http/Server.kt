package com.example.xavier.http

import com.example.xavier.app.api.FieldConstant.Companion.GID
import com.example.xavier.app.api.FieldConstant.Companion.PAGE
import com.example.xavier.app.api.FieldConstant.Companion.PID
import com.example.xavier.bean.result.*
import com.example.xavier.bean.result.dataclass.DetailsData
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Server {

    // 版本更新
    @POST("v3/home/release")
    @FormUrlEncoded
    fun versionUpdating(@FieldMap map: Map<String, String>): Observable<BaseData<VersionUpdatingResult>>

    //  头部分类
    @POST("v3/home/topClass")
    fun topClass(): Observable<BaseData<List<TopClassResult>>>

    //  首页轮播图
    @POST("v3/home/slideShow")
    @FormUrlEncoded
    fun slideShow(@Field(PID) pid: Int): Observable<BaseData<List<SlideShowResult>>>

    // 首页广告
    @POST("v3/home/ad")
    fun homeAd(): Observable<BaseData<List<HomeAdResult>>>

    // 二级分类
    @POST("v3/home/subClass")
    @FormUrlEncoded
    fun subClass(@Field(PID) pid: Int): Observable<BaseData<List<SubClassResult>>>

    // 精选好店
    @POST("v3/home/choiceShop")
    fun choiceShop(): Observable<BaseData<List<List<ChoiceShopResult>>>>

    //  热门推荐
    @POST("v3/home/hot")
    @FormUrlEncoded
    fun hot(@Field(PAGE) page: Int): Observable<BaseData<HotResult>>

    //  分类商品
    @POST("v3/home/classGoods")
    @FormUrlEncoded
    fun classGoods(@FieldMap map: MutableMap<String, Int?>): Observable<BaseData<HotResult>>

    /**
     * @method 判断是否有未结束的
     * @url v3/live/anchor/getUserHasLiveDemand
     */
    @POST("v3/live/anchor/getUserHasLiveDemand")
    fun getUserHasLiveDemand(): Observable<BaseData<String>>

    //  商品详情
    @POST("v3/goods/details")
    @FormUrlEncoded
    fun details(@Field(GID) gid: Int): Observable<BaseData<DetailsData>>

}