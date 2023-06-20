package menus.menuListaMarcaReloj

import menus.menuMarcaReloj.MenuMR
import java.util.*

class MenuListaMR {

    companion object {

        private val comandos: Map<Int, Comando> = mapOf(
            1 to Opcion1MostrarMR(),
            2 to Opcion2AgregarMR(),
            3 to Opcion3SeleccionarMR(),
            4 to Opcion4EliminarMR()
        )

        fun mostrarOpciones() {
            println("Sistema de Gestión de Relojes")
            println("1. Mostrar las Marcas de Relojes")
            println("2. Agregar una Marca de Reloj")
            println("3. Seleccionar una Marca de Reloj")
            println("4. Eliminar una marca de reloj")
            println("5. Salir")
        }

        fun ejecutarOpcion(opcion: Int){
            val comando = comandos[opcion]
            comando?.ejecutar()
        }

        fun ejecutarMenu(){

            var bandera: Int = 0
            val scanner = Scanner(System.`in`)
            while(bandera != 5){
                mostrarOpciones()
                print("Elija una opición: ")
                bandera = scanner.nextInt()
                ejecutarOpcion(bandera)
            }
        }
    }
}