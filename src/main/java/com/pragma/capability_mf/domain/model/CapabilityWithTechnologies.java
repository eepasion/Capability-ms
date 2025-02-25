package com.pragma.capability_mf.domain.model;

import java.util.List;

public record CapabilityWithTechnologies(String id, String name, String description, List<Technology> technologies) {
}
