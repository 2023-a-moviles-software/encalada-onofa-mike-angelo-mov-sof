package menus.menuMarcaReloj

import ListaMarcaReloj
import MarcaReloj
import java.util.Scanner

class MenuMR {

    companion object {

        private val comandos: Map<Int, ComandoMR> = mapOf(
            1 to Opcion1MostrarR(),
            2 to Opcion2AgregarR(),
            3 to Opcion3EditarMR(),
            4 to Opcion4SeleccionarR(),
            5 to Opcion5EliminarR()
        )

        private fun mostrarOpciones() {
            println("Sistema de Gestión de Relojes")
            println("1. Mostrar relojes")
            println("2. Agregar un reloj")
            println("3. Editar esta marca de relojes")
            println("4. Seleccionar un reloj")
            println("5. Eliminar un reloj")
            println("6. Atras")
        }

        private fun ejecutarOpcion(opcion: Int, marcaReloj: MarcaReloj){
            val comando = comandos[opcion]
            comando?.ejecutar(marcaReloj)
        }

        fun ejecutarMenu(marcaReloj: MarcaReloj){

            var bandera: Int = 0
            val scanner = Scanner(System.`in`)
            while(bandera != 6){
                mostrarOpciones()
                print("Elija una opición: ")
                bandera = scanner.nextInt()
                ejecutarOpcion(bandera, marcaReloj)
            }
        }
    }
}