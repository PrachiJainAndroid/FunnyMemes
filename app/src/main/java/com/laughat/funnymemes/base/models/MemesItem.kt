package com.laughat.funnymemes.base.models

import android.os.Parcelable
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.laughat.funnymemes.database.Converters

@Entity(tableName = "Memes_Table")
@TypeConverters(Converters::class)
@Parcelize
data class MemesItem(

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("width")
    var width: Int? = null,

    @PrimaryKey
    @NonNull
    @field:SerializedName("id")
    var id: Int ,

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("height")
    var height: Int? = null,

    @field:SerializedName("box_count")
    var boxCount: Int? = null
): Parcelable