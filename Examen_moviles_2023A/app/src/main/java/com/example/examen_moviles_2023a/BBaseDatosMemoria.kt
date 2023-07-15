package com.example.examen_moviles_2023a


class BBaseDatosMemoria {

    companion object{

        var arregloBMarcaReloj = arrayListOf<BMarcaReloj>()

        init{
            arregloBMarcaReloj.add(BMarcaReloj("Rolex", "Suiza", "10/10/1987", 4))
            arregloBMarcaReloj.get(0).getRelojes().add(BReloj("FDT-545", 54.25,  "15/11/2003"))
            arregloBMarcaReloj.get(0).getRelojes().add(BReloj("TDR-651", 85.25, "06/06/2005"))

            arregloBMarcaReloj.add(BMarcaReloj("Casio", "Alemania", "05/11/1956", 5))
            arregloBMarcaReloj.get(1).getRelojes().add(BReloj("452-FDG", 48.25,  "24/09/2011"))
            arregloBMarcaReloj.get(1).getRelojes().add(BReloj("845-GDS", 96.45,  "29/06/2009"))
        }


    }

}