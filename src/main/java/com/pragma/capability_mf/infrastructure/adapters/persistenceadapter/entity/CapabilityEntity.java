package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "capabilities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapabilityEntity {
    @Id
    private String id;
    @Field("nombre")
    private String name;
    @Field("descripcion")
    private String description;
    @Field("tecnologias")
    private Long[] technologies;
}
