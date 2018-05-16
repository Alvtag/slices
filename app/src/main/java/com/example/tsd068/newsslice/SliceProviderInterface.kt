package com.example.tsd068.newsslice

import android.net.Uri
import androidx.slice.builders.ListBuilder

/**
 * Created by tsd068 on 2018-05-15.
 */
interface SliceProviderInterface {
    fun listBuilder(sliceUri: Uri?): ListBuilder

    fun rowBuilder(listBuilder: ListBuilder): ListBuilder.RowBuilder
}