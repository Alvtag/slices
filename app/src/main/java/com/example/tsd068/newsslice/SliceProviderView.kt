package com.example.tsd068.newsslice

import android.content.ContentResolver
import android.content.Context
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri
import android.util.Log
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.core.SliceHints
import com.example.tsd068.Logg
import com.example.tsd068.model.NewsStory


class SliceProviderView(val sliceProviderInterface: SliceProviderInterface) {

    companion object Test {
        lateinit var newsList: List<NewsStory>

        fun updateStoryList(context: Context, freshNewsList: List<NewsStory>, urlBuilder: Uri.Builder) {
            newsList = freshNewsList
            // Should notify the URI to let any slices that might be displaying know to update.
            val uri = getUri(context, "/topnews", urlBuilder)
            context.contentResolver.notifyChange(uri, null)
        }

        fun getUri(context: Context, path: String, uriBuilder: Uri.Builder): Uri {

            return uriBuilder
                    .scheme(ContentResolver.SCHEME_CONTENT)
                    .authority(context.packageName)
                    .appendPath(path)
                    .build()
        }
    }

    //take in a uri and returns a slice. or null.
    fun onBindSlice(sliceUri: Uri, sliceAction: SliceAction, icon: IconCompat): Slice? {
        Logg.instance.d("ALVTAG", "sliceUri.path:" + sliceUri.path)
        when (sliceUri.path) {
            "/topnews" -> {

                //newsList.get()
                val listBuilder = sliceProviderInterface.listBuilder(sliceUri)

                val titleRowBuilder = sliceProviderInterface.rowBuilder(listBuilder)
                titleRowBuilder.setTitleItem(icon, SliceHints.ICON_IMAGE)
                titleRowBuilder.setTitle("ABC News")
                titleRowBuilder.setPrimaryAction(sliceAction)
                listBuilder.addRow(titleRowBuilder)


                val itemRowBuilder = sliceProviderInterface.rowBuilder(listBuilder)
                itemRowBuilder.setTitleItem(icon, SliceHints.ICON_IMAGE)
                itemRowBuilder.setTitle("Foo Story")
                itemRowBuilder.setSubtitle("Description lorem ipsum dolor...")
                listBuilder.addRow(itemRowBuilder)

                val slice = listBuilder.build()
                //TODO("kick off network task to fetch stories")
                // Build the slice
                return slice
            }
        }
        return null
    }
}
