package com.example.permissionhandling

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ActivityCompat
import com.example.permissionhandling.PermissionManager.PermissionAskListener

import android.provider.MediaStore

import android.content.Intent
import android.content.DialogInterface
import android.net.Uri
import android.provider.Settings
import android.widget.Toast

import android.content.pm.PackageManager
import android.util.Log

import androidx.annotation.NonNull





class MainActivity : AppCompatActivity() {
    private lateinit var permissionManager : PermissionManager
    companion object{
        const val REQUEST_CAMERA = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionManager = PermissionManager(this)
        btn_open_camera.setOnClickListener{
            permissionManager.checkPermission(
                this,
                Manifest.permission.CAMERA,
                object : PermissionAskListener {
                    override fun onNeedPermission() {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA
                        )
                    }

                    override fun onPermissionPreviouslyDenied() {
                        showCameraRational()
                    }

                    override fun onPermissionPreviouslyDeniedWithNeverAskAgain() {
                        dialogForSettings(
                            "Permission Denied",
                            "Now you must allow camera access from settings."
                        )
                    }

                    override fun onPermissionGranted() {
                        openCamera()
                    }
                })
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    private fun dialogForSettings(title: String, msg: String) {
        AlertDialog.Builder(this).setTitle(title).setMessage(msg)
            .setCancelable(false)
            .setNegativeButton("NOT NOW"
            ) {
                    dialog, _ -> dialog.dismiss() }
            .setPositiveButton("SETTINGS") { dialog, _ ->
                goToSettings()
                dialog.dismiss()
            }.show()
    }

    private fun goToSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri: Uri = Uri.parse("package:$packageName")
        intent.data = uri
        startActivity(intent)
    }

    private fun showCameraRational() {
       AlertDialog.Builder(this).setTitle("Permission Denied")
           .setMessage("Without this permission this app is unable to open camera to take your photo. Are you sure you want to deny this permission.")
           .setCancelable(false)
           .setNegativeButton("I'M SURE"
           ) { dialog, _ -> dialog.dismiss() }
           .setPositiveButton("RETRY") {
                   dialog, _ ->
               ActivityCompat.requestPermissions(
                   this@MainActivity,
                   arrayOf(Manifest.permission.CAMERA),
                   REQUEST_CAMERA
               )
               dialog.dismiss()
           }.show()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}