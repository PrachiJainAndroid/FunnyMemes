package com.laughat.funnymemes.base.toolkit

import com.google.gson.Gson


interface ToolKit{


    /**
     * Returns Single Instance of Gson to make Code Reusable
     *
     * @return Gson
     */
    fun getGson(): Gson
}