import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class AlmacenamientoDatos {

    companion object {

        fun guardarDatos() {

            val file = FileOutputStream("data.dat")
            val objectOutputStream = ObjectOutputStream(file)
            objectOutputStream.writeObject(ListaMarcaReloj.getMarcasReloj())

            objectOutputStream.close()
            file.close()
        }

        fun recuperarDatos() {
            val file = FileInputStream("data.dat")
            val objectInputStream = ObjectInputStream(file)

            val listaMarcasReloj = objectInputStream.readObject() as ArrayList<MarcaReloj>
            objectInputStream.close()
            file.close()

            ListaMarcaReloj.setMarcasReloj(listaMarcasReloj)


        }

    }

}