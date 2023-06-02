package com.middleware.msmiddleware.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "empleados")
public class MiddlewareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Column(name = "tipo_documento_empleado")
    private String tipoDocumentoEmpleado;
    @Column(name = "identificador_empleado")
    private String identificadorEmpleado;
    @Column(name = "nombres_empleado")
    private String nombresEmpleado;
    @Column(name = "apellidos_empleado")
    private String apellidosEmpleado;
    @Column(name = "direccion_empleado")
    private String direccionEmpleado;
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;
    @Column(name = "correo_empleado")
    private String correoEmpleado;
    @Column(name = "fecha_nacimiento_empleado")
    private String fechaNacimientoEmpleado;

}
