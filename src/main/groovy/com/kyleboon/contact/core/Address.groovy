package com.kyleboon.contact.core

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull

@ToString
@Entity
@Table(name = 'addresses')
@EqualsAndHashCode
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    Long id

    @NotNull
    @NotEmpty
    @Column(name = 'address1', nullable = false)
    @JsonProperty
    String address1

    @Column(name = 'address2', nullable = true)
    @JsonProperty
    String address2

    @NotNull
    @NotEmpty
    @Column(name = 'city', nullable = false)
    @JsonProperty
    String city

    @NotNull
    @NotEmpty
    @Column(name = 'state', nullable = false)
    @JsonProperty
    String state

    @NotNull
    @NotEmpty
    @Column(name = 'county', nullable = false)
    @JsonProperty
    String county

    @NotNull
    @NotEmpty
    @Column(name = 'zip_code', nullable = false)
    @JsonProperty
    String zipCode
}
