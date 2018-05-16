package com.example.tsd068.newsslice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by tsd068 on 2018-05-16.
 */
class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if (ACTION_CHANGE_TEMP == action && intent.extras != null) {
            //val newValue = intent.extras!!.getInt(EXTRA_TEMP_VALUE, sTemperature)
            //updateTemperature(context)
        }
    }

    companion object {

        var ACTION_CHANGE_TEMP = "com.android.example.slicecodelab.ACTION_CHANGE_TEMP"
        var EXTRA_TEMP_VALUE = "com.android.example.slicecodelab.EXTRA_TEMP_VALUE"
    }

}
