package com.example.examen_moviles_2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperMarcaReloj(
    val contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaMarcaReloj =
            """
                CREATE TABLE MARCA_RELOJ(
                    id_mr INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    paisOrigen VARCHAR(50),
                    fechaFundacion VARCHAR(50),
                    ratingPopularidad INTEGER
                )
            """.trimIndent()

        val scriptSQLCrearTablaReloj =
            """
                CREATE TABLE RELOJ(
                    id_r INTEGER PRIMARY KEY,
                    modelo VARCHAR(50),
                    precio DOUBLE,
                    fechaFabricacion VARCHAR(50),
                    marca_id INTEGER,
                    FOREIGN KEY (marca_id) REFERENCES MARCA_RELOJ(id_mr) ON DELETE CASCADE
                    
                )
            """.trimIndent()

        db?.execSQL(scriptSQLCrearTablaMarcaReloj)
        db?.execSQL(scriptSQLCrearTablaReloj)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE RELOJ")
        db?.execSQL("DROP TABLE MARCA_RELOJ")
        onCreate(db)
    }

    fun crearMarcaReloj(nombre: String, paisOrigen: String, fechaFundacion: String, ratingPopularidad: Int): Boolean{
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("paisOrigen", paisOrigen)
        valoresAGuardar.put("fechaFundacion", fechaFundacion)
        valoresAGuardar.put("ratingPopularidad", ratingPopularidad)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "MARCA_RELOJ",
                null ,
                valoresAGuardar
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }

    fun eliminarMarcaReloj(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MARCA_RELOJ",
                "id_mr=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarMarcaReloj(nombre: String, paisOrigen: String, fechaFundacion: String, ratingPopularidad: Int, id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("paisOrigen", paisOrigen)
        valoresAActualizar.put("fechaFundacion", fechaFundacion)
        valoresAActualizar.put("ratingPopularidad", ratingPopularidad)

        val parametrosConsultaActualizar = arrayOf(id.toString())

        val resultadoActualizacion = conexionEscritura
            .update(
                "MARCA_RELOJ",
                valoresAActualizar,
                "id_mr=?",
                parametrosConsultaActualizar
            )

        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun consultarMarcaRelojPorID(id:Int): BMarcaReloj{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM MARCA_RELOJ WHERE id_mr = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val marcaRelojEncontrado = BMarcaReloj(0,"", "", "", 0)


        if(resultadoConsultaLectura.moveToFirst()){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val pasiOrigen = resultadoConsultaLectura.getString(2)
                val fechaFundacion = resultadoConsultaLectura.getString(3)
                val ratingPopularidad = resultadoConsultaLectura.getInt(4)

                marcaRelojEncontrado.id = id
                marcaRelojEncontrado.nombre = nombre
                marcaRelojEncontrado.paisOrigen = pasiOrigen
                marcaRelojEncontrado.setFechaFundacion(fechaFundacion)
                marcaRelojEncontrado.ratingPopularidad = ratingPopularidad

            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return marcaRelojEncontrado
    }

    fun consultarTodasLasMarcasReloj(): ArrayList<BMarcaReloj> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM MARCA_RELOJ
    """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val marcasReloj = ArrayList<BMarcaReloj>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val paisOrigen = resultadoConsultaLectura.getString(2)
                val fechaFundacion = resultadoConsultaLectura.getString(3)
                val ratingPopularidad = resultadoConsultaLectura.getInt(4)

                val marcaReloj = BMarcaReloj(id, nombre, paisOrigen, fechaFundacion, ratingPopularidad)
                marcasReloj.add(marcaReloj)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return marcasReloj
    }

    fun crearReloj(modelo: String, precio: Double, fechaFabricacion: String, marca_id: Int): Boolean{
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("modelo", modelo)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("fechaFabricacion", fechaFabricacion)
        valoresAGuardar.put("marca_id", marca_id)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "RELOJ",
                null,
                valoresAGuardar
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }


    fun eliminarReloj(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "RELOJ",
                "id_r=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }



    fun actualizarReloj(modelo: String, precio: Double, fechaFabricacion: String, id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("modelo", modelo)
        valoresAActualizar.put("precio", precio)
        valoresAActualizar.put("fechaFabricacion", fechaFabricacion)

        val parametrosConsultaActualizar = arrayOf(id.toString())

        val resultadoActualizacion = conexionEscritura
            .update(
                "RELOJ",
                valoresAActualizar,
                "id_r=?",
                parametrosConsultaActualizar
            )

        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }


    fun consultarRelojPorID(id:Int): BReloj{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLextura = """
            SELECT * FROM RELOJ WHERE id_r = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLextura, //Consulta
            parametrosConsultaLectura, //Parametros
        )

        val existeReloj = resultadoConsultaLectura.moveToFirst()
        val relojEncontrado = BReloj(0, "", 0.0, "")

        val arreglo = arrayListOf<BReloj>()
        if(existeReloj){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val modelo = resultadoConsultaLectura.getString(1)
                val precio = resultadoConsultaLectura.getDouble(2)
                val fechaFabricacion = resultadoConsultaLectura.getString(3)

                if(id != null){
                    relojEncontrado.id = id
                    relojEncontrado.modelo = modelo
                    relojEncontrado.precio = precio
                    relojEncontrado.setFechaFabricacion(fechaFabricacion)
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return relojEncontrado
    }


    fun consultarTodosLosRelojesDeMR(id_mr: Int): ArrayList<BReloj> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM RELOJ WHERE marca_id = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(id_mr.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura)

        val relojes = ArrayList<BReloj>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val modelo = resultadoConsultaLectura.getString(1)
                val precio = resultadoConsultaLectura.getDouble(2)
                val fechaFabricacion = resultadoConsultaLectura.getString(3)

                val reloj = BReloj(id, modelo, precio, fechaFabricacion)
                relojes.add(reloj)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return relojes
    }

    override fun toString(): String {
        return this.contexto.toString()
    }

}