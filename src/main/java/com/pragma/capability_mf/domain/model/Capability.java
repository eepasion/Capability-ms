package com.pragma.capability_mf.domain.model;

import java.util.List;

public record Capability(String id, String name, String description, List<Long> technologies) {
}
