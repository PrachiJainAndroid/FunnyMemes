package com.laughat.funnymemes.repository.remote

import com.laughat.funnymemes.base.toolkit.ToolKit

interface RepoManager {

    fun <T> getApi(baseUrl: String, apiClass: Class<T>): T?

    /**
     * Return TookLit which is a set of Tools Offered By BaseModule.
     *
     * @return Toolkit Instance
     */
    fun getToolKit(): ToolKit
}