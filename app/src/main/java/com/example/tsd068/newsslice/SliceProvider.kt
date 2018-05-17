package com.example.tsd068.newsslice

import android.app.PendingIntent
import android.content.Context
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri
import android.content.Intent
import androidx.core.graphics.drawable.IconCompat
import com.example.tsd068.Logg
import com.example.tsd068.MainActivity

class SliceProvider : SliceProvider(), SliceProviderInterface {

    val sliceProviderView = SliceProviderView(this)
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice {
        Logg.instance.d("ALVTAG", "SliceProvider onBindSlice")
        Logg.instance.d("ALVTAG", "SliceProvider : sliceUri.path:" + sliceUri?.path)
        sliceUri?.let {
            // Set the primary action; this will activate when the row is tapped
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(getContext(),
                    sliceUri.hashCode(),
                    intent, 0)
            val openTempActivity = SliceAction(pendingIntent,
                    IconCompat.createWithResource(context, android.R.drawable.btn_default),
                    "Temperature controls")
            return sliceProviderView.onBindSlice(sliceUri, openTempActivity)!! //onBindSLice is nullable ooops TODO ALVTAG
        }
        throw IllegalArgumentException("sliceUri cannot be null!")
    }

    override fun listBuilder(sliceUri: Uri?): ListBuilder {
        return ListBuilder(context, sliceUri, 9999999999L)
    }

    override fun rowBuilder(listBuilder: ListBuilder): ListBuilder.RowBuilder {
        return ListBuilder.RowBuilder(listBuilder)
    }

    override fun uriBuilder(): Uri.Builder {
        return Uri.Builder()
    }

    override fun context(): Context {
        return context
    }

    override fun appIcon(): IconCompat {
        return IconCompat.createWithResource(context, R.drawable.abc)
    }

    override fun rowIcon(): IconCompat {
        return IconCompat.createWithResource(context, R.drawable.newspaper_512)
    }

}