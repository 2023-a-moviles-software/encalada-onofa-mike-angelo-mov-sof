package com.example.examen_moviles_2023a

import android.content.Context

/*
class EBaseDeDatos {

    companion object{
        var tablaMarcaReloj: ESqliteHelperMarcaReloj? = null
    }
}

 */

class EBaseDeDatos {

    companion object {
        private var _tablaMarcaReloj: ESqliteHelperMarcaReloj? = null

        val tablaMarcaReloj: ESqliteHelperMarcaReloj
            get() {
                if (_tablaMarcaReloj == null) {
                    throw IllegalStateException("La instancia de ESqliteHelperMarcaReloj no ha sido inicializada.")
                }
                return _tablaMarcaReloj!!
            }

        fun inicializar(context: Context) {
            if (_tablaMarcaReloj == null) {
                _tablaMarcaReloj = ESqliteHelperMarcaReloj(context)
            }
        }

        override fun toString(): String {
            return tablaMarcaReloj.toString()
        }
    }
}
