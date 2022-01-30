package com.laughat.funnymemes.repository.remote

import java.util.WeakHashMap


/**
 * The Class Which Keeps the RetroFit Api References and Returns the Same Reference if Required.
 *
 * As Its a WeakHashMap, It will Remove all those Instances which are not going to use or not used for a while and Claim Memory Easily.
 *
 */

internal class ApiPool : WeakHashMap<String, Any>()