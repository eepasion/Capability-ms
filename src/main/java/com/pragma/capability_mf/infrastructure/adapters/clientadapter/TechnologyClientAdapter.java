package com.pragma.capability_mf.infrastructure.adapters.clientadapter;

import com.pragma.capability_mf.domain.model.Technology;
import com.pragma.capability_mf.domain.spi.TechnologyClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@Slf4j
public class TechnologyClientAdapter implements TechnologyClientPort {
    private final RSocketRequester rsocketRequester;

    public TechnologyClientAdapter(RSocketRequester rsocketRequester) {
        this.rsocketRequester = rsocketRequester;
    }

    public Mono<List<Technology>> getAllTechnologiesById(List<Long> ids) {
        return rsocketRequester
                .route("get.technologies")
                .data(ids)
                .retrieveMono(new ParameterizedTypeReference<List<Technology>>() {});
    }

}
