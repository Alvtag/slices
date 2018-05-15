package com.example.tsd068.newsslice

import android.annotation.SuppressLint
import android.app.slice.Slice
import android.app.slice.SliceProvider
import android.app.slice.SliceSpec
import android.net.Uri

/**
 * Created by tsd068 on 2018-05-15.
 */

class NewsSliceProvider:SliceProvider(){
    override fun onCreate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    // override: we're using android P dev preview (28), somehow android studio doesn't realize we're targeting 28
    @SuppressLint("Override")
    override fun onBindSlice(sliceUri: Uri?, supportedSpecs: MutableSet<SliceSpec>?): Slice {
        return super.onBindSlice(sliceUri, supportedSpecs)
    }
}