package com.laughat.funnymemes.repository.remote

import com.laughat.funnymemes.base.toolkit.ToolKit
import retrofit2.Retrofit

internal class AppRepoManager constructor(
    private val retrofit: Retrofit.Builder,
    private val apiPool: ApiPool,
    private val toolKit: ToolKit):RepoManager{



    /**
     * The Generic Custom Signed Api Provider Which can be Accessed from Any Consumer Module and Provides the Api Interface object Reference Generated by RetroFit.
     *
     * @param T the Type of Api Interface
     * @param baseUrl Your Base URL
     * @param apiClass Class Of Type of your Api Interface
     * @return the Api Interface object
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> getApi(baseUrl: String, apiClass: Class<T>): T? {
        return provideApi(baseUrl,apiClass,retrofit)
    }

    /**
     * Return TookLit which is a set of Tools Offered By BaseModule.
     *
     * @return Toolkit Instance
     */
    override fun getToolKit(): ToolKit {
        return toolKit
    }

    private fun <T> provideApi(
        baseUrl: String,
        apiClass: Class<T>,
        retrofit: Retrofit.Builder
    ): T? {
        val key = baseUrl + apiClass.hashCode()
        if (apiPool.contains(key).not())
            apiPool[key] = retrofit.baseUrl(baseUrl).build().create(apiClass)
        return apiPool[key] as T
    }


}