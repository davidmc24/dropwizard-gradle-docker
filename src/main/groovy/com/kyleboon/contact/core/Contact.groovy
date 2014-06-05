package com.kyleboon.contact.core

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * User: kboon
 * Date: 11/19/12
 */
@Entity
@Table(name = 'contacts')
@ToString
@EqualsAndHashCode
class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    Long id

    @NotNull
    @NotEmpty
    @Column(name = 'first_name', nullable = false)
    @JsonProperty
    String firstName

    @NotNull
    @NotEmpty
    @Column(name = 'last_name', nullable = false)
    @JsonProperty
    String lastName

    @NotNull
    @NotEmpty
    @Column(name = 'job_title', nullable = false)
    @JsonProperty
    String jobTitle

    @NotNull
    @NotEmpty
    @Column(name = 'phone_number', nullable = false)
    @JsonProperty
    String phoneNumber


    @JoinColumn(name = 'address_id')
    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    Address address
}
