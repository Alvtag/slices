package com.example.tsd068

import android.util.Log

/**
 * Created by tsd068 on 2018-05-16.
 */
class Logg{
    companion object {
        var instance: Logg = Logg()
    }

    fun d (tag:String, msg:String){
        Log.d(tag, msg)
    }
    fun e (tag:String, msg:String){
        Log.d(tag, msg)
    }
    fun i (tag:String, msg:String){
        Log.d(tag, msg)
    }
    fun v (tag:String, msg:String){
        Log.d(tag, msg)
    }
}