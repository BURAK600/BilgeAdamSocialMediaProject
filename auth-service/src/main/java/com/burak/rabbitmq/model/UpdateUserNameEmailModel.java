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
public class UpdateUserNameEmailModel implements Serializable {
    private Long id;
    private String userName;
    private String email;

}
