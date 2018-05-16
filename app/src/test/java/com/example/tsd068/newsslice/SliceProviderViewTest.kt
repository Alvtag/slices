package com.example.tsd068.newsslice

import android.net.Uri
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Created by tsd068 on 2018-05-15.
 */
class SliceProviderViewTest {

    val newsSliceProviderInterface = Mockito.mock(SliceProviderInterface::class.java)

    val viewUnderTest = SliceProviderView(newsSliceProviderInterface)

    @Before
    fun setUp() {

    }


    @Test
    fun onBindSlice() {
        /* Given */
        val slice = Mockito.mock(Slice::class.java)
        val uri = Mockito.mock(Uri::class.java)
        `when`(uri.path).then { "/temperature" }
        val action = Mockito.mock(SliceAction::class.java)
        val listBuilder = Mockito.mock(ListBuilder::class.java)
        `when`(listBuilder.build()).then { slice }
        val rowBuilder = Mockito.mock(ListBuilder.RowBuilder::class.java)



        /* When */
        val result = viewUnderTest.onBindSlice(uri, listBuilder, rowBuilder, action)

        /* Then */
        assertEquals(slice, result)
    }


}