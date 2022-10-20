package com.burak.dto.request;

import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivatedRequestDto {

    private Long id;
    private String activatedCode;


}
