package com.ascend.example.demospringboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user_name")
    private String name;

    @JsonProperty("user_email")
    private String email;

    @JsonProperty("user_adress")
    private String address;
}
