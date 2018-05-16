package com.example.tsd068.newsslice

import android.annotation.SuppressLint
import android.app.PendingIntent
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri
import android.content.Intent
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.SliceSpecs
import com.example.tsd068.MainActivity

/**
 * Created by tsd068 on 2018-05-15.
 */

class SliceProvider : SliceProvider(), SliceProviderInterface {

    val sliceProviderView = SliceProviderView(this)
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice {

        sliceUri?.let {

            val listBuilder = ListBuilder(context, sliceUri, 9999999999L)

            val rowBuilder = ListBuilder.RowBuilder(listBuilder)
            // Set the primary action; this will activate when the row is tapped
            val intent = Intent(getContext(), MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(getContext(), sliceUri?.hashCode() ?: -1,
                    intent, 0)
            val openTempActivity = SliceAction(pendingIntent,
                    IconCompat.createWithResource(context, android.R.drawable.btn_default).toIcon(),
                    "Temperature controls")
            return sliceProviderView.onBindSlice(sliceUri, listBuilder, rowBuilder, openTempActivity)!!
        }
        throw IllegalArgumentException("sliceUri cannot be null!");
    }
}