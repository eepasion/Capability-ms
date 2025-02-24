package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository;

import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.entity.CapabilityEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityRepository extends ReactiveMongoRepository<CapabilityEntity, String> {
}
