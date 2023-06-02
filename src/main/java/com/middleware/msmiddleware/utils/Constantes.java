package com.libertadores.pensiones.utils;

public class Constantes {
    private Constantes() {
        throw new IllegalStateException("Utility class");
    }
    /*
    Mensajes
     */
    public static final String MENSAJE_ERROR_NULO_VACIO = "no puede estar vacío o ser nulo";
    public static final String SAVE_DATA = "Se ejecuto la insercion de datos con exito";
    public static final String UPDATE_DATA = "Se Actualizo los datos exitosamente";
    public static final String DELETE_DATA = "Se eliminaron los datos exitosamente";
    /*
    Errores
     */

    public static final String MSUS_01 = "Para creacion de usuario no se debe enviar ID";
    public static final String MSUS_02 = "EL ID no puede ser nulo";
    public static final String MSUS_03 = "No se encontro un registyro con ese ID";
}
