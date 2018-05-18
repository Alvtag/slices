package com.example.tsd068.newsslice

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import com.example.tsd068.Logg
import com.example.tsd068.model.NewsStory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.*

@Suppress("MemberVisibilityCanPrivate", "HasPlatformType", "FunctionName")
class SliceProviderViewTest {
    val slice = Mockito.mock(Slice::class.java)
    val newsSliceProviderInterface = Mockito.mock(SliceProviderInterface::class.java)
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
        /* Given a good path and a list*/
        val story1 = mock(NewsStory::class.java)
        val story2 = mock(NewsStory::class.java)
        val story3 = mock(NewsStory::class.java)
        val stories = ArrayList<NewsStory>()

        val titleRowBuilder = mock(ListBuilder.RowBuilder::class.java)
        val row1Builder = mock(ListBuilder.RowBuilder::class.java)
        val row2Builder = mock(ListBuilder.RowBuilder::class.java)
        val row3Builder = mock(ListBuilder.RowBuilder::class.java)

        `when`(newsSliceProviderInterface.rowBuilder(listBuilder))
                .thenReturn(titleRowBuilder)
                .thenReturn(row1Builder)
                .thenReturn(row2Builder)
                .thenReturn(row3Builder)

        stories.add(story1)
        stories.add(story2)
        stories.add(story3)
        SliceProviderView.newsList = stories
        `when`(listBuilder.build()).thenReturn(slice)

        /* When null*/
        val result = viewUnderTest.onBindSlice(uri)

        /* Then gimme a slice*/
        assertEquals(slice, result)
        verify(listBuilder).addRow(titleRowBuilder)
        verify(listBuilder).addRow(row1Builder)
        verify(listBuilder).addRow(row2Builder)
        verify(listBuilder).addRow(row3Builder)
    }

    @Test(expected = IllegalArgumentException::class)
    fun onBindSlice_badPath() {
        /* Given a bad path*/
        `when`(uri.path).then { "/temperature" }

        /* When onBindSlice*/
        val result = viewUnderTest.onBindSlice(uri)

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
