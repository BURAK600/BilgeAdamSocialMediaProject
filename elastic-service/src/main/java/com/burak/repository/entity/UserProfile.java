package com.burak.repository.entity;

import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 * NoSQL olan elasticsearch tablo yerine document mantığı ile çalışır.
 */
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    private String id;
    private Long authId;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private String about;
    private Long created;
    private Long updated;
    @Builder.Default
    Status status= Status.PENDING;



}
