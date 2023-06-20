import menus.menuListaMarcaReloj.MenuListaMR
import java.io.FileNotFoundException

fun main(args: Array<String>) {

   try {
      AlmacenamientoDatos.recuperarDatos()
   }catch (e: FileNotFoundException){

   }

   MenuListaMR.ejecutarMenu()


}