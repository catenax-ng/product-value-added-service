package com.catenax.valueaddedservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Country.
 */
@Entity
@Table(name = "t_country")
@Setter
@Getter
@ToString
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "country", nullable = false, unique = true)
    private String country;

    @Size(max = 3)
    @Column(name = "iso_3", length = 3)
    private String iso3;

    @Size(max = 2)
    @Column(name = "iso_2", length = 2)
    private String iso2;

    @Column(name = "continent")
    private String continent;


}
