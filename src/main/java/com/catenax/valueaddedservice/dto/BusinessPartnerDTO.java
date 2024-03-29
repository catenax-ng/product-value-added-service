package com.catenax.valueaddedservice.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BusinessPartnerDTO implements Serializable {
    private Long id;

    @Schema(example = "BPN-NUMBER")
    private String bpn;

    @Schema(example = "Divape Company")
    private String legalName;

    @Schema(example = "1st")
    private String street;

    @Schema(example = "Sutteridge")
    private String houseNumber;

    @Schema(example = "633104")
    private String zipCode;

    @Schema(example = "Covilhã")
    private String city;

    @Schema(example = "Portugal")
    private String country;

    @Schema(example = "107.6185727")
    private String longitude;

    @Schema(example = "-6.6889038")
    private String latitude;

    public BusinessPartnerDTO(String json) {

        try {
            BusinessPartnerDTO businessPartnerDTO = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(json, BusinessPartnerDTO.class);
            this.bpn = businessPartnerDTO.getBpn();
            this.country = businessPartnerDTO.getCountry();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
