package com.middleware.msmiddleware.persistence.repository;

import com.middleware.msmiddleware.persistence.entity.MiddlewareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiddlewareRepository extends JpaRepository<MiddlewareEntity, Integer> {
}
