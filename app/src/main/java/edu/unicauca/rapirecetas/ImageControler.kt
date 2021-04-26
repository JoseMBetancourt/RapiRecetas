package edu.unicauca.rapirecetas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

object ImageControler {
    fun selectPhotoFromGallery(activity: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/."

        activity.startActivityForResult(intent, code)

    }

    fun saveImage(context: Context,id:Long,uri:Uri){
        val file= File(context.filesDir,id.toString())

        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!

        file.writeBytes(bytes)
    }

    fun getImageUri(context: Context,ide:Long):Uri{
        val file = File(context.filesDir, ide.toString())
        return if (file.exists()) Uri.fromFile(file)
        else Uri.parse("android.resource://edu.unicauca.rapirecetas/drawable/comidas_mundo")
    }

    fun deleteImage(context: Context, id: Long){
        var file = File(context.filesDir, id.toString())
        file.delete()
    }
}