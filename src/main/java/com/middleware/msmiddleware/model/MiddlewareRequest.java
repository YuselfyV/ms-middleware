package com.middleware.msmiddleware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiddlewareRequest {
    private Integer idEmpleado;
    private String tipoDocumentoEmpleado;
    private String identificadorEmpleado;
    private String nombresEmpleado;
    private String apellidosEmpleado;
    private String direccionEmpleado;
    private String telefonoEmpleado;
    private String correoEmpleado;
    private String fechaNacimientoEmpleado;
}
