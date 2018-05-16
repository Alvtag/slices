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

    override fun uriBuilder(): Uri.Builder {
        return Uri.Builder()
    }

    override fun context(): Context {
        return context
    }

     fun getChangeTempIntent(value: Int): PendingIntent {
        val intent = Intent(MyBroadcastReceiver.ACTION_CHANGE_TEMP)
        intent.setClass(context, MyBroadcastReceiver::class.java!!)
        intent.putExtra(MyBroadcastReceiver.EXTRA_TEMP_VALUE, value)
        return PendingIntent.getBroadcast(context,0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
    }

}