package menus.menuListaMarcaReloj

import ListaMarcaReloj
import java.util.Scanner

class Opcion4EliminarMR: Comando {
    override fun ejecutar() {
        val scanner = Scanner(System.`in`);

        print("Ingrese el índice de la marca de reloj: ")
        val indice = scanner.nextInt()

        ListaMarcaReloj.getMarcasReloj().removeAt(indice);
        AlmacenamientoDatos.guardarDatos()
    }
}