package com.catenax.valueaddedservice.dto;

import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BusinessPartnerDTO implements Serializable {
    private Long id;

    private String bpn;

    private String legalName;

    private String address;

    private String city;

    private String country;
}
