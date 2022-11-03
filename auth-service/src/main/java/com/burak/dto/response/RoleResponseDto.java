package com.burak.dto.response;

import com.burak.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponseDto {

    private Long id;
    private String userName;
    private String roles;
}
