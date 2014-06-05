package com.kyleboon.contact.core

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

@ToString
@EqualsAndHashCode
class SearchCommand {
    @NotNull
    @NotEmpty
    @JsonProperty
    String firstName


}
