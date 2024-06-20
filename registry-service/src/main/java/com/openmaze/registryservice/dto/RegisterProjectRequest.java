package com.openmaze.registryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProjectRequest {
    private String name;
    private String organization;
    private String url;
    private String projectType;
}
