package com.laughat.funnymemes.repository.local



import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.amnix.xtension.logs.L

import io.reactivex.Single
import java.lang.ref.WeakReference

@SuppressLint("ApplySharedPref")
 class AppSecureSharedPreferences(private val sharedPreferences: WeakReference<SharedPreferences?>?) :
    SecureSharedPreference {
    override fun remove(key: String): Single<Boolean> = Single.create {
        try {
            it.onSuccess(sharedPreferences?.get()?.edit()?.remove(key)?.commit()!!)
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun clear(): Single<Boolean> = Single.create {
        try {
            it.onSuccess(sharedPreferences?.get()?.edit()?.clear()?.commit()!!)
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun getBoolean(key: String?, defValue: Boolean): Single<Boolean> = Single.create {
        try {
            val str = sharedPreferences?.get()?.getString(key.toString(), defValue.toString())
            if (str == null || str == defValue.toString())
                it.onSuccess(defValue)
            else
                it.onSuccess(str.toString().toBoolean())
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun getInt(key: String?, defValue: Int): Single<Int> = Single.create {
        try {
            val str = sharedPreferences?.get()?.getString(key.toString(), defValue.toString())
            if (str == null || str == defValue.toString())
                it.onSuccess(defValue)
            else
                it.onSuccess((str.toString()).toInt())
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun getLong(key: String?, defValue: Long): Single<Long> = Single.create {
        try {
            val str = sharedPreferences?.get()?.getString((key.toString()), defValue.toString())
            if (str == null || str == defValue.toString())
                it.onSuccess(defValue)
            else
                it.onSuccess((str.toString()).toLong())
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun getFloat(key: String?, defValue: Float): Single<Float> = Single.create {
        try {
            val str = sharedPreferences?.get()?.getString((key.toString()), defValue.toString())
            if (str == null || str == defValue.toString())
                it.onSuccess(defValue)
            else
                it.onSuccess((str.toString()).toFloat())
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun getString(key: String?, defValue: String?): Single<String> = Single.create {
        try {
            val str = sharedPreferences?.get()?.getString(key.toString(), defValue)
            if (str == null || str == defValue)
                it.onSuccess(defValue.toString())
            else
                it.onSuccess(str.toString())
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    override fun putInt(key: String, value: Int) = putInternal(key, value)

    override fun putLong(key: String, value: Long) = putInternal(key, value)

    override fun putFloat(key: String, value: Float) = putInternal(key, value)

    override fun putString(key: String, value: String) = putInternal(key, value)

    override fun putBoolean(key: String, value: Boolean) = putInternal(key, value)

    override fun contains(key: String): Single<Boolean> = Single.create {
        try {
            it.onSuccess(sharedPreferences?.get()?.contains((key)) ?: false)
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

    private fun putInternal(key: String, value: Any): Single<Boolean> = Single.create {
        try {
            it.onSuccess(
                sharedPreferences!!.get()!!.edit()!!.putString((key), (value.toString())
                )!!.commit()
            )
        } catch (t: Throwable) {
            it.onError(t)
            L.wtf(t)
        }
    }

}