package com.kyleboon.contact.core

import com.fasterxml.jackson.databind.ObjectMapper
import io.dropwizard.testing.FixtureHelpers
import spock.lang.Specification

class ContactSpec extends Specification {
    Address address
    Contact contact

    //private final static CONTACT_FIXTURE = 'fixtures/contact.json'

    //ObjectMapper objectMapper = new ObjectMapper()


   def setup() {

       address = new Address(
               id:0,
               address1:'15 South 5th Street',
               address2:'',
               city:'Minneapolis',
               state:'MN',
               county:'Hennepin',
               zipCode:'55402'
       )

       contact = new Contact(
               id:0,
               firstName:'Kyle',
               lastName:'Boon',
               jobTitle:'developer',
               phoneNumber:'999-999-9999',
               address: address
       )
   }

//    def 'serializes to json as expected'() {
//        when:
//        def expectedJSON = FixtureHelpers.fixture(CONTACT_FIXTURE)
//        Contact actualCommand = objectMapper.readValue(expectedJSON, Contact)
//        def actualJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualCommand)
//
//
//        then:
//        assert expectedJSON == actualJSON
//    }
//
//    def 'deserializes from json as expected'() {
//        when:
//        def expectedJSON = FixtureHelpers.fixture(CONTACT_FIXTURE)
//        def actualCommand = objectMapper.convertValue(expectedJSON, Contact)
//
//        then:
//        assert actualCommand == contact
//    }

}
