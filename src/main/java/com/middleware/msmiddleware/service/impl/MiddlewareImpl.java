package com.middleware.msmiddleware.service.impl;

import static com.libertadores.pensiones.utils.Constantes.MSUS_01;
import static com.libertadores.pensiones.utils.Constantes.MSUS_02;
import static com.libertadores.pensiones.utils.Constantes.MSUS_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.middleware.msmiddleware.model.MiddlewareRequest;
import com.middleware.msmiddleware.model.MiddlewareResponse;
import com.middleware.msmiddleware.persistence.entity.MiddlewareEntity;
import com.middleware.msmiddleware.persistence.repository.MiddlewareRepository;
import com.middleware.msmiddleware.service.MiddlewarelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libertadores.pensiones.utils.Excepcion;

@Service
public class MiddlewareImpl implements MiddlewarelService {

    @Autowired
    MiddlewareRepository empleadoRepository;

    /**
     *Solicita Datos de entidad empleado
     * @return ArrayList<UserEntity>
     */
    public List<MiddlewareResponse> getEmpleado() {
        List<MiddlewareEntity> entity = new ArrayList<>();
        Iterable<MiddlewareEntity> userModelIterable;
        userModelIterable = empleadoRepository.findAll();
        userModelIterable.forEach(entity::add);
        List<MiddlewareResponse> userLista = entity.stream().map(userEntity -> transformEmpleadoResponse(userEntity)).collect(Collectors.toList());
        return userLista;
    }

    /**
     * Guardado de datos en la entidad empleado
     * @param entity
     * @return UserResponse
     */
    @Override
    public MiddlewareResponse saveEmpleado(MiddlewareRequest entity) {
        if (entity.getIdEmpleado() != null) {
            throw new Excepcion(MSUS_01);
        }
        return transformEmpleadoResponse(empleadoRepository.save(transformEmpleadoEntity(entity)));
    }

    /**
     * Actualizacion de datos en la entidad empleado
     * @param request
     * @return UserResponse
     */
    @Override
    public MiddlewareResponse updateEmpleado(MiddlewareRequest request) {
        if (request.getIdEmpleado() == null) {
            throw new Excepcion(MSUS_02);
        }
        MiddlewareResponse userId = getEmpleadoID(request.getIdEmpleado());
        if (userId != null) {
            return transformEmpleadoResponse(empleadoRepository.save(transformEmpleadoEntity(request)));
        } else {
            throw new Excepcion(MSUS_03);
        }
    }

    /**
     * Eliminar registro por ID en la entidad empleado
     * @param id
     */

    @Override
    public void deleteEmpleadoID(Integer id) {
        empleadoRepository.deleteById(id);
    }

    /**
     * Obtener datos por ID en la entidad empleado
     * @param id
     * @return UserResponse
     */
    @Override
    public MiddlewareResponse getEmpleadoID(Integer id) {
        Optional<MiddlewareEntity> optionalUserEntity = empleadoRepository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            throw new Excepcion(MSUS_03);
        }
        return transformEmpleadoResponse(optionalUserEntity.get());
    }

    /**
     * Transformar el request a la entidad empleado
     * @param request
     * @return UserEntity
     */
    private MiddlewareEntity transformEmpleadoEntity(MiddlewareRequest request) {
        MiddlewareEntity entity = new MiddlewareEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    /**
     * transformacio de la respuesta final para el usuario
     * @param entity
     * @return UserResponse
     */
    private MiddlewareResponse transformEmpleadoResponse(MiddlewareEntity entity) {
        MiddlewareResponse response = new MiddlewareResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


}
