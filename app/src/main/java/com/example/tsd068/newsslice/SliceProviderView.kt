package com.example.tsd068.newsslice

import android.content.ContentResolver
import android.content.Context
import androidx.slice.Slice
import androidx.slice.builders.SliceAction
import android.net.Uri
import androidx.slice.core.SliceHints
import com.example.tsd068.model.NewsStory


class SliceProviderView(val sliceProviderInterface: SliceProviderInterface) {

    var presenter = SliceProviderPresenter(this)
    companion object Test {
        var newsList: List<NewsStory>? = null

        fun getUri(context: Context, path: String, uriBuilder: Uri.Builder): Uri {
            return uriBuilder
                    .scheme(ContentResolver.SCHEME_CONTENT)
                    .authority(context.packageName)
                    .appendPath(path)
                    .build()
        }
    }

    fun updateStoryList(  freshNewsList: List<NewsStory> ) {
        newsList = freshNewsList
        val context = sliceProviderInterface.context()
        // Should notify the URI to let any slices that might be displaying know to update.
        val uri = getUri(context, "/topnews", sliceProviderInterface.uriBuilder())

        context.contentResolver.notifyChange(uri, null)
    }

    //take in a uri and returns a slice. or null.
    fun onBindSlice(sliceUri: Uri, sliceAction: SliceAction): Slice? {
        when (sliceUri.path) {
            "/topnews" -> {
                System.out.println(newsList);
                val listBuilder = sliceProviderInterface.listBuilder(sliceUri)

                val titleRowBuilder = sliceProviderInterface.rowBuilder(listBuilder)
                titleRowBuilder.addEndItem(sliceProviderInterface.appIcon(), SliceHints.SMALL_IMAGE)
                titleRowBuilder.setTitle("ABC News")
                titleRowBuilder.setPrimaryAction(sliceAction)
                listBuilder.addRow(titleRowBuilder)

                newsList?.let {
                    it.forEach {
                        val itemRowBuilder = sliceProviderInterface.rowBuilder(listBuilder)
                        itemRowBuilder.setTitleItem(sliceProviderInterface.rowIcon(), SliceHints.SMALL_IMAGE)
                        itemRowBuilder.setTitle(it.title)
                        itemRowBuilder.setSubtitle(it.description)
                        listBuilder.addRow(itemRowBuilder)
                    }
                }

                presenter.fetchArticles()
                return listBuilder.build()
            }
        }
        throw IllegalArgumentException("${sliceUri.path} is not a recognized path!")
    }
}
