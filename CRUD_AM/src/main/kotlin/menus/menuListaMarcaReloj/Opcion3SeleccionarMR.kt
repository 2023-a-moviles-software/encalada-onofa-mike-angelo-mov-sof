package menus.menuListaMarcaReloj

import ListaMarcaReloj
import menus.menuMarcaReloj.MenuMR
import java.util.Scanner

class Opcion3SeleccionarMR: Comando {
    override fun ejecutar() {
        var scanner: Scanner = Scanner(System.`in`)

        print("Ingrese el ID de la marca de reloj: ")
        var indice: Int = scanner.nextInt()



        MenuMR.ejecutarMenu(ListaMarcaReloj.getMarcaRelojByIndex(indice))
    }
}