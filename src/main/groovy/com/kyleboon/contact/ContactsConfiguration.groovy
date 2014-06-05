package com.kyleboon.contact

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * User: kboon
 * Date: 11/14/12
 */
class ContactsConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty('database')
    DataSourceFactory database = new DataSourceFactory()
}
