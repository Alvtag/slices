package com.example.tsd068.newsslice

import android.app.PendingIntent
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri
import android.content.Intent
import androidx.core.graphics.drawable.IconCompat
import com.example.tsd068.MainActivity

class SliceProvider : SliceProvider(), SliceProviderInterface {

    val sliceProviderView = SliceProviderView(this)
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice {

        sliceUri?.let {
            // Set the primary action; this will activate when the row is tapped
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(getContext(),
                    sliceUri.hashCode() ?: -1,
                    intent, 0)
            val openTempActivity = SliceAction(pendingIntent,
                    IconCompat.createWithResource(context, android.R.drawable.btn_default),
                    "Temperature controls")
            val icon = IconCompat.createWithResource(context, R.drawable.newspaper_512);
            return sliceProviderView.onBindSlice(sliceUri, openTempActivity, icon)!!
        }
        throw IllegalArgumentException("sliceUri cannot be null!");
    }

    override fun listBuilder(sliceUri: Uri?): ListBuilder {
        return ListBuilder(context, sliceUri, 9999999999L)
    }

    override fun rowBuilder(listBuilder: ListBuilder): ListBuilder.RowBuilder {
        return ListBuilder.RowBuilder(listBuilder)
    }
}