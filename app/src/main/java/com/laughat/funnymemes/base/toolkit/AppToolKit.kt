package com.laughat.funnymemes.base.toolkit

import com.google.gson.Gson


/**
 *  The AppToolKit Which Provide Related Tools Within the baseModule Context.
 */
class AppToolKit( private val gson : Gson) : ToolKit{
    /**
     * Returns Single Instance of Gson to make Code Reusable
     *
     * @return Gson
     */
    override fun getGson() = gson



}