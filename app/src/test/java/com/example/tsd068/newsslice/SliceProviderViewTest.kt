package com.example.tsd068.newsslice

import android.app.slice.Slice
import android.net.Uri
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
        `when`(uri.path)
                .then { "content://com.android.example.slicecodelab/temperature" }


        /* When */
        val result = viewUnderTest.onBindSlice(uri)

        /* Then */
        assertEquals(slice, result)
    }

    @Test
    fun onBindSlice_badUri() {
        /* Given */

        val slice = Mockito.mock(Slice::class.java)


        val uri = Mockito.mock(Uri::class.java)
        `when`(uri.path)
                .then { "content://com.android.example.slicecodelab/location" }


        /* When */
        val result = viewUnderTest.onBindSlice(uri)

        /* Then */
        assertEquals(null, result)
    }

}