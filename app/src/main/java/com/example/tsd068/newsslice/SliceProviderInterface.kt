package com.example.tsd068.newsslice

import android.content.Context
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction

/**
 * Created by tsd068 on 2018-05-15.
 */
interface SliceProviderInterface {
    fun listBuilder(sliceUri: Uri?): ListBuilder

    fun rowBuilder(listBuilder: ListBuilder): ListBuilder.RowBuilder

    fun uriBuilder():Uri.Builder

    fun context():Context

    fun appIcon():IconCompat

    fun rowIcon():IconCompat

    fun appAction():SliceAction

    fun storyAction(url:String):SliceAction
}