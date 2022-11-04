package com.burak.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ActivatedRequestDto implements Serializable {

    private Long id;
    private String activatedCode;
    private String email;
}
