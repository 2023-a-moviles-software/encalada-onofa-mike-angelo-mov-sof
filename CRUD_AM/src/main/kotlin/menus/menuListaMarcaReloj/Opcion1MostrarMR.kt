package menus.menuListaMarcaReloj

import ListaMarcaReloj

class Opcion1MostrarMR: Comando {
    override fun ejecutar() {


        for ((indice, valor) in ListaMarcaReloj.getMarcasReloj().withIndex()){
            println("===================================================")
            println("ID: $indice")
            println("$valor")
        }
        println("===================================================")

        //println(ListaMarcaReloj.getMarcasReloj())
    }
}