import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    val inmutable: String = "Mike";
    //inmutable = "Encalada";

    var mutable: String= "Encalada";
    mutable = "Mike";

    // val > var

    //Duck typing
    val ejemploVariable = "Mike Encalada"
    val edadEjemplo: Int = 23
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //Variable primitiva
    val nombreProfesor: String = "Mike Encalada"
    val sueldo: Double = 1.2
    var estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val fechaNacimiento: Date = Date()

    //No existe el switch, pero si el when
    val estadoCivilWhen = "C"
    when(estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else ->{
            println("no sabemos")
        }
    }
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    calcularSueldo(10.0)
    calcularSueldo(10.0, 20.0)
    calcularSueldo(10.0, 20.0, 30.0)
    calcularSueldo(10.0, bonoEspecial = 30.0)
    calcularSueldo(sueldo=10.0, tasa = 45.0, bonoEspecial = 18.0 )

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)



    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3);
    println(arregloEstatico)

    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos

    // FOR EACH -> Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloDinamico.forEach { println(it) } // it (en ingles eso) significa el elemento iterado

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR ->  ANY (Alguno cumple?)
    // AND ->  ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) // 78

}


fun imprimirNombre(nombre:String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.0, //opcional(por defecto)
    bonoEspecial: Double? = null, //opcional (nullable) --> ? Nos indica que puede ser null
): Double{
    if (bonoEspecial == null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa)+bonoEspecial
    }
}


abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class numeros(//constructor primario
    //ejemplo:
    //uno: Int (Parametro (sin modificar acceso))
    //private var uno : Int (Propiedad publica Clase numeros )
    //var uno:Int Propiedad de la clase por defecto publica
    protected val numeroUno:Int, //propiedad de la clase protected numeros.numeroUno
    protected val numeroDos:Int,
){
    init {
        this.numeroUno; this.numeroDos;//No es necesario el uso de this
        numeroUno; numeroDos;
        println("Inicializando")

    }
}

class Suma(//Constructor Primario Suma
    uno: Int, //Parametro
    dos: Int //Parametro
): numeros(uno, dos) { // <- Constructor padre
    init { //Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }
    constructor( // Segundo constructor
        uno: Int?, //parametros
        dos: Int //parametros
    ): this(//llamada constructor primario
        if(uno==null) 0 else uno,
        dos
    ){//si necesitamos bloque de codigo lo usamos
        numeroUno
    }
    constructor(//tercer constructor
        uno:Int,
        dos: Int?
    ): this(
        uno,
        if (dos==null) 0 else dos
    )// si no necesitamos el bloque de codigo "{}" lo omitios

    constructor( //cuarto constructor
        uno: Int?,
        dos: Int?
    ): this(
        if ( uno == null ) 0 else uno,
        if ( dos == null ) 0 else dos
    )
    public fun sumar(): Int{
        val total = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{//atributos y metodos compartidos
    //entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}




