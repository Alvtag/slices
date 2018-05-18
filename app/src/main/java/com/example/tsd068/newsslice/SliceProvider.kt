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
import com.example.tsd068.MainActivity

class SliceProvider : SliceProvider(), SliceProviderInterface {

    private val sliceProviderView = SliceProviderView(this)
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice {
        sliceUri?.let {

            return sliceProviderView.onBindSlice(sliceUri)!!
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

    override fun appAction(): SliceAction {
        // Set the primary action; this will activate when the row is tapped
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val action = SliceAction(
                pendingIntent,
                IconCompat.createWithResource(context, android.R.drawable.btn_default),
                "Regular App")
        return action
    }

    override fun storyAction(url: String): SliceAction {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("url", url)
        val pendingIntent = PendingIntent.getActivity(context,
                url.hashCode(),
                intent, 0)
        val action = SliceAction(
                pendingIntent,
                IconCompat.createWithResource(context, android.R.drawable.btn_default),
                "story App")
        return action
    }
}