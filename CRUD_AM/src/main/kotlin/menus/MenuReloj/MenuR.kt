package menus.MenuReloj


import Reloj
import java.util.*

class MenuR {
    companion object {

        private val comandos: Map<Int, ComandoR> = mapOf(
            1 to Opcion1EditarR()
        )

        private fun mostrarOpciones() {
            println("Sistema de Gestión de Relojes")
            println("1. Edita Reloj")
            println("2. Atras")
        }

        private fun ejecutarOpcion(opcion: Int, reloj: Reloj){
            val comando = comandos[opcion]
            comando?.ejecutar(reloj)
        }

        fun ejecutarMenu(reloj: Reloj){

            var bandera: Int = 0
            val scanner = Scanner(System.`in`)
            while(bandera != 2){
                mostrarOpciones()
                print("Elija una opición: ")
                bandera = scanner.nextInt()
                ejecutarOpcion(bandera, reloj)
            }
        }
    }
}