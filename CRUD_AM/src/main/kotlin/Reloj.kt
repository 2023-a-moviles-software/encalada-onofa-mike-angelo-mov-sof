import java.io.Serializable
import java.util.Date

class Reloj(modelo: String, precio: Double, esDigital: Boolean, tipo: Char?, fechaFabricacion: Date): Serializable {

    private var modelo: String
    private var precio: Double
    private var esDigital: Boolean
    private var tipo: Char = 'P'
    private var fechaFabricacion: Date

    init{
        this.modelo = modelo
        this.precio = precio
        this.esDigital = esDigital
        if (tipo != null) {
            this.tipo = tipo
        }
        this.fechaFabricacion = fechaFabricacion
    }

    fun getModelo(): String{
        return this.modelo
    }

    fun getPrecio(): Double{
        return this.precio
    }

    fun getEsDigital(): Boolean{
        return this.esDigital
    }

    fun getTipo(): Char{
        return this.tipo
    }

    fun getFechaFabricacion(): Date{
        return this.fechaFabricacion
    }

    fun setModelo(modelo: String){
        this.modelo = modelo;
    }

    fun setPrecio(precio: Double){
        this.precio = precio
    }

    fun setEsDigital(digital: Boolean){
        this.esDigital = digital
    }

    fun setTipo(tipo: Char?){
        if (tipo != null) {
            this.tipo = tipo
        }
    }

    fun setFechaFabricacion(fechaFabricacion: Date){
        this.fechaFabricacion = fechaFabricacion
    }

    override fun toString(): String {
        return "Reloj(\n" +
                "    modelo='$modelo', \n" +
                "    precio=$precio, \n" +
                "    esDigital=$esDigital, \n" +
                "    tipo=$tipo, \n" +
                "    fechaFabricacion=$fechaFabricacion)\n"
    }


}