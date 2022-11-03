package com.burak.dto.response;

import com.burak.repository.enums.Roles;
import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivePendingUserResponseDto {

    private String email;
    private Roles role;
    private Status status;

}
