package com.middleware.msmiddleware.service;

import com.middleware.msmiddleware.model.MiddlewareRequest;
import com.middleware.msmiddleware.model.MiddlewareResponse;

import java.util.List;

public interface MiddlewarelService {

    List<MiddlewareResponse> getEmpleado();

    MiddlewareResponse saveEmpleado(MiddlewareRequest request);

    MiddlewareResponse updateEmpleado(MiddlewareRequest request);

    void deleteEmpleadoID(Integer id);

    MiddlewareResponse getEmpleadoID(Integer id);

}
