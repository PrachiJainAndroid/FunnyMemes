package com.laughat.funnymemes.repository.local


import io.reactivex.Single

/**
 * SharedPreference Which is Secured Through AES and Based on Reactive Programming through RxJava
 */
interface SecureSharedPreference {
    /**
     * Checks whether the preferences contains a preference.
     *
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     *         otherwise false.
     */
    fun contains(key: String): Single<Boolean>

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue through RxJava Single
     *
     */
    fun getBoolean(key: String?, defValue: Boolean): Single<Boolean>

    /**
     * Retrieve an int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue through RxJava Single
     *
     */
    fun getInt(key: String?, defValue: Int): Single<Int>

    /**
     * Retrieve a long value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue through RxJava Single
     *
     */
    fun getLong(key: String?, defValue: Long): Single<Long>

    /**
     * Retrieve a float value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue through RxJava Single
     *
     */
    fun getFloat(key: String?, defValue: Float): Single<Float>

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue through RxJava Single
     */
    fun getString(key: String?, defValue: String?): Single<String>

    /**
     * Set a boolean value in the preferences editor, to be written back
     * once {@link #commit} or {@link #apply} are called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to save the Value or Not.
     */
    fun putBoolean(key: String, value: Boolean): Single<Boolean>

    /**
     * Set an int value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to save the Value or Not.
     */
    fun putInt(key: String, value: Int): Single<Boolean>

    /**
     * Set a long value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to save the Value or Not.
     */
    fun putLong(key: String, value: Long): Single<Boolean>

    /**
     * Set a float value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to save the Value or Not.
     */
    fun putFloat(key: String, value: Float): Single<Boolean>

    /**
     * Set a String value in the preferences editor, to be written back once
     * {@link #commit} or {@link #apply} are called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to save the Value or Not.
     */
    fun putString(key: String, value: String): Single<Boolean>

    /**
     * Mark in the editor that a preference value should be removed, which
     * will be done in the actual preferences once {@link #commit} is
     * called.
     *
     * <p>Note that when committing back to the preferences, all removals
     * are done first, regardless of whether you called remove before
     * or after put methods on this editor.
     *
     * @param key The name of the preference to remove.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to remove the Value or Not.
     */
    fun remove(key: String): Single<Boolean>

    /**
     * Mark in the editor to remove <em>all</em> values from the
     * preferences.  Once commit is called, the only remaining preferences
     * will be any that you have defined in this editor.
     *
     * <p>Note that when committing back to the preferences, the clear
     * is done first, regardless of whether you called clear before
     * or after put methods on this editor.
     *
     * @return Returns a Boolean Status through RxJava Single to let Consumer Know Weather we were able to clear All the Values or Not.
     */
    fun clear(): Single<Boolean>
}