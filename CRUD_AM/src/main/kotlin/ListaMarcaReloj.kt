import java.io.Serializable

class ListaMarcaReloj : Serializable {


    companion object {
        private var marcasReloj: ArrayList<MarcaReloj> = arrayListOf<MarcaReloj>();

        fun getMarcasReloj(): ArrayList<MarcaReloj> {
            return this.marcasReloj
        }

        fun agregarMarcaReloj(marcaReloj: MarcaReloj) {
            marcasReloj.add(marcaReloj)
        }

        fun getMarcaRelojByIndex(indice: Int): MarcaReloj {
            return marcasReloj.get(indice)
        }

        fun eliminarMarcaReloj(indice: Int) {
            marcasReloj.removeAt(indice)
        }

        fun setMarcasReloj(marcasReloj: ArrayList<MarcaReloj>){
            this.marcasReloj = marcasReloj
        }
    }
}