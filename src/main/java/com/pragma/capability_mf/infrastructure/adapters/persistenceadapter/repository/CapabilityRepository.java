package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository;

import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.entity.CapabilityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CapabilityRepository extends ReactiveMongoRepository<CapabilityEntity, String> {
    @Query("{}")
    Flux<CapabilityEntity> findAllWithPagination(Pageable pageable);

    @Aggregation(pipeline = {
            "{ $addFields: { numTecnologias: { $size: '$tecnologias' } } }",
            "{ $sort: { numTecnologias: ?0 } }",
            "{ $skip: ?1 }",
            "{ $limit: ?2 }"
    })
    Flux<CapabilityEntity> findAllSortedByTecnhnologies(int sortDirection, int skip, int limit);

    @Aggregation(pipeline = {
            "{ $addFields: { lowerName: { $toLower: '$nombre'} } }",
            "{ $sort: { lowerName: ?0 } }",
            "{ $skip: ?1 }",
            "{ $limit: ?2 }"
    })
    Flux<CapabilityEntity> findAllSortedByName(int sortDirection, int skip, int limit);

    @Aggregation(pipeline = {
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    Flux<CapabilityEntity> findAllWithPagination(int skip, int limit);
}
