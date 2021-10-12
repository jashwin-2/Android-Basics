package com.example.permissionhandling

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences




class SessionManager(private val context: Context, private val MY_PREF: String = "my_preferences", ) {
    var sharedPreferences: SharedPreferences? = context.getSharedPreferences(MY_PREF, MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = null

    fun firstTimeAskingPermission(permission: String?, isFirstTime: Boolean) {
        doEdit()
        editor!!.putBoolean(permission, isFirstTime)
        doCommit()
    }

    fun isFirstTimeAskingPermission(permission: String?): Boolean {
        return sharedPreferences!!.getBoolean(permission, true)
    }

    private fun doEdit() {
        if (editor == null) {
            editor = sharedPreferences!!.edit()
        }
    }

    private fun doCommit() {
        if (editor != null) {
            editor!!.commit()
            editor = null
        }
    }
}