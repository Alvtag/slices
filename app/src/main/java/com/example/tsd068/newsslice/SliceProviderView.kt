package com.example.tsd068.newsslice

/**
 * Created by tsd068 on 2018-05-15.
 */
import androidx.slice.Slice
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import android.net.Uri
import com.example.tsd068.model.NewsStory


class SliceProviderView{

    val sliceProviderInterface: SliceProviderInterface

    companion object Test {
        lateinit var newsList:List<NewsStory>



    }
    constructor(sliceProviderInterface: SliceProviderInterface) {
        this.sliceProviderInterface = sliceProviderInterface
    }


    //take in a uri and returns a slice. or null.
    fun onBindSlice(sliceUri: Uri,listBuilder: ListBuilder, rowBuilder:ListBuilder.RowBuilder, sliceAction: SliceAction): Slice? {
        when (sliceUri.path) {
            "/topnews" -> {

                //newsList.get()



                rowBuilder.setTitle("news")
                rowBuilder.setPrimaryAction(sliceAction)
                listBuilder.addRow(rowBuilder)
                val slice = listBuilder.build()

                // Build the slice
                return slice
            }
        }
        return null
    }
}
