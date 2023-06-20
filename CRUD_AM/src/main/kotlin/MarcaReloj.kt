import java.io.Serializable
import java.util.Date

class MarcaReloj (nombre: String, paisOrigen: String, fechaFundacion: Date, ratingPopularidad: Int): Serializable {

    private var nombre: String
    private var paisOrigen: String
    private var fechaFundacion: Date
    private var ratingPopularidad: Int
    private var relojes: ArrayList<Reloj>

    init {
        this.nombre = nombre
        this.paisOrigen = paisOrigen
        this.fechaFundacion = fechaFundacion
        this.ratingPopularidad = ratingPopularidad
        this.relojes = arrayListOf<Reloj>()
    }

    fun getNombre(): String{
        return this.nombre
    }

    fun getPaisOrigen(): String{
        return this.paisOrigen
    }

    fun getFechFundacion(): Date{
        return this.fechaFundacion
    }

    fun getRatingPopularidad(): Int{
        return this.ratingPopularidad
    }

    fun getRelojes(): List<Reloj>{
        return this.relojes
    }

    fun setNombre(nombre: String){
        this.nombre = nombre;
    }

    fun setPaiOrigen(paisOrigen: String){
        this.paisOrigen = paisOrigen
    }

    fun setFechaFundacion(fechaFundacion: Date){
        this.fechaFundacion = fechaFundacion
    }

    fun setRatingPopularidad(ratingPopularidad: Int){
        this.ratingPopularidad = ratingPopularidad
    }

    fun agregarReloj(reloj: Reloj){
        relojes.add(reloj)
    }

    fun getRelojByIndex(indice: Int): Reloj{
        return relojes.get(indice)
    }

    fun elminarRelojByIndex(indice: Int){
        this.relojes.removeAt(indice)
    }

    override fun toString(): String {
        return "MarcaReloj(\n" +
                "     nombre='$nombre', \n" +
                "     paisOrigen='$paisOrigen', \n" +
                "     fechaFundacion=$fechaFundacion, \n" +
                "     ratingPopularidad=$ratingPopularidad, \n)"
    }


}