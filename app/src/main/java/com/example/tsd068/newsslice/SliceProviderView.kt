package com.example.tsd068.newsslice

/**
 * Created by tsd068 on 2018-05-15.
 */
import android.app.slice.Slice
import android.net.Uri

class SliceProviderView{

    val sliceProviderInterface: SliceProviderInterface

    constructor(sliceProviderInterface: SliceProviderInterface) {
        this.sliceProviderInterface = sliceProviderInterface
    }


    //take in a uri and returns a slice. or null.
    fun onBindSlice(sliceUri: Uri): Slice? {


        return null
    }
}