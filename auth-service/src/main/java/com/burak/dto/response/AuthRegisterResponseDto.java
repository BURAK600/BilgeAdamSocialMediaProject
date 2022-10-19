package com.burak.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRegisterResponseDto {
    private Long id;
    private String userName;
    private String activatedCode;
}
