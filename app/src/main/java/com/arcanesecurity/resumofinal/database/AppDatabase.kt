package com.arcanesecurity.resumofinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arcanesecurity.resumofinal.database.dao.PixabayDao
import com.arcanesecurity.resumofinal.model.Image

/**
 * Classe abstrata para criar nosso database local
 * Devemos passar na annotation @Database os parametros:
 * @param entities Array com a lista de classes com a annotation @Entity
 * @param version Int versao do banco (Quando qualquer coisa for alterada em relaçao
 * ao banco este numero deve ser incrementado)
 */
@Database(
    entities = [
        Image::class
    ],
    version = 2,

    )
abstract class AppDatabase : RoomDatabase() {

    /**
     * funcao declarada para o Room implementar automaticamente nosso DAO
     */
    abstract fun getPixabayDao(): PixabayDao

    companion object {

        /**
         * Singleton que irá gerar nossa classe AppDatabse com tudo implementado pelo Room.
         */
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "resumo_final_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}
