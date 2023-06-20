package menus.menuMarcaReloj

import MarcaReloj

class Opcion1MostrarR: ComandoMR {
    override fun ejecutar(marcaReloj: MarcaReloj) {


        for ((indice, valor) in marcaReloj.getRelojes().withIndex()){
            println("===================================================")
            println("ID: $indice")
            println("$valor")
        }
        println("===================================================")

        //println(marcaReloj.getRelojes())
    }

}