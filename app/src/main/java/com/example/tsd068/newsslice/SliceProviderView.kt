package com.example.tsd068.newsslice

/**
 * Created by tsd068 on 2018-05-15.
 */
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri


class SliceProviderView{

    val sliceProviderInterface: SliceProviderInterface

    constructor(sliceProviderInterface: SliceProviderInterface) {
        this.sliceProviderInterface = sliceProviderInterface
    }


    //take in a uri and returns a slice. or null.
    fun onBindSlice(sliceUri: Uri,listBuilder: ListBuilder, rowBuilder:ListBuilder.RowBuilder, sliceAction: SliceAction): Slice? {
        when (sliceUri.path) {
            "/topnews" -> {
                rowBuilder.setTitle("news")
                rowBuilder.setPrimaryAction(sliceAction)
                listBuilder.addRow(rowBuilder)

                // Build the slice
                return listBuilder.build()
            }
        }
        return null
    }
}
