package com.example.tsd068.newsslice

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import com.example.tsd068.Logg
import com.example.tsd068.model.NewsStory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class SliceProviderViewTest {
    val slice = Mockito.mock(Slice::class.java)
    val newsSliceProviderInterface = Mockito.mock(SliceProviderInterface::class.java)
    val icon = Mockito.mock(IconCompat::class.java)
    val action = Mockito.mock(SliceAction::class.java)
    val listBuilder = Mockito.mock(ListBuilder::class.java)
    val rowBuilder = Mockito.mock(ListBuilder.RowBuilder::class.java)
    val context = Mockito.mock(Context::class.java)
    val uriBuilder = Mockito.mock(Uri.Builder::class.java)
    val presenter = Mockito.mock(SliceProviderPresenter::class.java)
    lateinit var uri:Uri

    val viewUnderTest = SliceProviderView(newsSliceProviderInterface)

    @Before
    fun setUp() {
        Logg.instance = Mockito.mock(Logg::class.java)

        uri = Mockito.mock(Uri::class.java)
        viewUnderTest.presenter = this.presenter
        `when`(uri.path).then { "/topnews" }
        `when`(listBuilder.build()).then { slice }

        `when`(newsSliceProviderInterface.listBuilder(uri)).thenReturn(listBuilder)
        `when`(newsSliceProviderInterface.rowBuilder(listBuilder)).thenReturn(rowBuilder)
        `when`(newsSliceProviderInterface.context()).thenReturn(context)
        `when`(newsSliceProviderInterface.uriBuilder()).thenReturn(uriBuilder)
    }

    @Test
    fun onBindSlice() {
        /* Given a good path*/
        `when`(listBuilder.build()).then { slice }
        val rowBuilder = Mockito.mock(ListBuilder.RowBuilder::class.java)

        /* When null*/
        val result = viewUnderTest.onBindSlice(uri, action, icon)

        /* Then gimme a slice*/
        assertEquals(slice, result)
    }

    @Test
    fun onBindSlice_badpath() {
        /* Given a bad path*/
        `when`(uri.path).then { "/temperature" }
        val action = Mockito.mock(SliceAction::class.java)

        /* When onBindSlice*/
        val result = viewUnderTest.onBindSlice(uri, action, icon)

        /* Then null*/
        assertEquals(null, result)
    }

    @Test
    fun getUri() {
        /* Given*/
        val path = "/fooPath"
        `when`(uriBuilder.build()).then { uri }
        `when`(uriBuilder.scheme(ContentResolver.SCHEME_CONTENT)).thenReturn(uriBuilder)
        `when`(uriBuilder.authority(context.packageName)).thenReturn(uriBuilder)
        `when`(uriBuilder.appendPath(path)).thenReturn(uriBuilder)

        /* When*/
        val result = SliceProviderView.getUri(context, path, uriBuilder)

        /* Then*/
        assertEquals(uri, result)
        verify(uriBuilder).scheme(ContentResolver.SCHEME_CONTENT)
        verify(uriBuilder).authority(context.packageName)
        verify(uriBuilder).appendPath(path)
    }

    @Test
    fun updateStoryList() {
        /* Given*/
        val storyList = ArrayList<NewsStory>()
        val contentResolver = Mockito.mock(ContentResolver::class.java)
        val path = "/topnews"
        `when`(uriBuilder.build()).then { uri }
        `when`(uriBuilder.scheme(ContentResolver.SCHEME_CONTENT)).thenReturn(uriBuilder)
        `when`(uriBuilder.authority(context.packageName)).thenReturn(uriBuilder)
        `when`(uriBuilder.appendPath(path)).thenReturn(uriBuilder)
        `when`(context.contentResolver).thenReturn(contentResolver)

        /* When*/
        viewUnderTest.updateStoryList(storyList)

        /* Then*/
        verify(contentResolver).notifyChange(uri, null)
        assertEquals(storyList, SliceProviderView.newsList)
    }
}
