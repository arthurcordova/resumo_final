package com.arcanesecurity.resumofinal.database

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream

object DatabaseUtil {
    suspend fun importDatabase(context: Context) {
        withContext(Dispatchers.IO) {
            try {
                val inputStream = context.assets.open("daily_stickers_database.db")
                val outFileName = File(context.getDatabasePath("daily_stickers_database").absolutePath)

                if(outFileName.exists()) {
                    outFileName.delete()
                }

                val output: OutputStream = FileOutputStream(outFileName)
                output.write(inputStream.readBytes())

                output.flush()
                output.close()
                inputStream.close()
            } catch (e: Exception) {
                Log.e(this::class.java.simpleName, e.toString())
            }
        }
    }

    suspend fun exportDatabase(context: Context){
        withContext(Dispatchers.IO) {
            try {
                val file = File(context.getDatabasePath("daily_stickers_database").absolutePath)
                val fis = FileInputStream(file)
                val outFileName = "${context.externalCacheDir}/daily_stickers_database.db"

                val output: OutputStream = FileOutputStream(outFileName)
                output.write(fis.readBytes())

                output.flush()
                output.close()
                fis.close()
            } catch (e: Exception) {
                Log.e(this::class.java.simpleName, e.toString())
            }
        }
    }
}