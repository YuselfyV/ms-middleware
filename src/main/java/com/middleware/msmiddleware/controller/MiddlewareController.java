package com.middleware.msmiddleware.controller;

import static com.libertadores.pensiones.utils.Constantes.DELETE_DATA;
import static com.libertadores.pensiones.utils.Constantes.SAVE_DATA;
import static com.libertadores.pensiones.utils.Constantes.UPDATE_DATA;

import java.util.Date;
import java.util.List;

import com.middleware.msmiddleware.configuration.KafkaProducer;
import com.middleware.msmiddleware.model.MiddlewareRequest;
import com.middleware.msmiddleware.model.MiddlewareResponse;
import com.middleware.msmiddleware.service.MiddlewarelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertadores.pensiones.utils.Response;

@RestController
@RequestMapping("libertadores")
public class MiddlewareController {

    private final KafkaProducer kafkaProducer;

    @Autowired
    public MiddlewareController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @Autowired
    MiddlewarelService service;


    @GetMapping(value = "/empleado/general", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<MiddlewareResponse> getEmpleado() {
        Date sysdate = new Date();
        List<MiddlewareResponse> response=  this.service.getEmpleado();
        kafkaProducer.enviarPeticion("Obteniendo datos del empleado: " + response +  " Fecha de la transaccion: " + sysdate);
        return response;
    }

    @PostMapping(value = "/empleado", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<MiddlewareResponse>> saveEmpleado(@RequestBody MiddlewareRequest entity) {
        Date sysdate = new Date();
        MiddlewareResponse response = service.saveEmpleado(entity);
        kafkaProducer.enviarPeticion("Creando datos del empleado: " + response + " Fecha de la transaccion: " + sysdate);
        return new ResponseEntity<>(new Response<>(response, SAVE_DATA), HttpStatus.OK);
    }

    @PutMapping(value = "/empleado", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<MiddlewareResponse>> updateEmpleado(@RequestBody MiddlewareRequest entity) {
        Date sysdate = new Date();
        MiddlewareResponse response = service.updateEmpleado(entity);
        kafkaProducer.enviarPeticion("Actualizando datos del empleado: " + response + " Fecha de la transaccion: " + sysdate );
        return new ResponseEntity<>(new Response<>(response, UPDATE_DATA), HttpStatus.OK);
    }

    @DeleteMapping(value = "/empleado/{id}")
    public ResponseEntity<Response<MiddlewareResponse>> deleteEmpleado(@Validated @PathVariable Integer id) {
        Date sysdate = new Date();
        kafkaProducer.enviarPeticion("Se va a eliminar empleado con id: " + id + " Fecha de la transaccion: " +sysdate );
        service.deleteEmpleadoID(id);
        return new ResponseEntity<>(new Response<>(null, DELETE_DATA), HttpStatus.OK);
    }

}
