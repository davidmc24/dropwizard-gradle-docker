package com.kyleboon.contact.resources

import com.kyleboon.contact.core.Contact
import com.kyleboon.contact.core.SearchCommand
import com.kyleboon.contact.db.ContactDAO
import spock.lang.Specification

class ContactResourceSpec extends Specification {
    ContactResource contactResource
    ContactDAO mockContactDAO
    Contact expectedContact

    def setup() {
        mockContactDAO = Mock()
        contactResource = new ContactResource(mockContactDAO)
        expectedContact = new Contact()
    }

    def 'create calls DAO'() {
        when: 'the create method is called'
        Contact actualContact = contactResource.createContact(expectedContact)

        then: 'it passes the contact to the DAO and does nothing else'
        1 * mockContactDAO.saveOrUpdate(expectedContact) >> expectedContact
        0 * _
        actualContact == expectedContact
    }

    def 'get calls DAO'() {
        when: 'the get method is called'
        Contact actualContact = contactResource.getContact(1)

        then: 'it passes the id to the DAO and returns the result'
        1 * mockContactDAO.findById(1) >> expectedContact
        0 * _
        actualContact == expectedContact
    }

    def 'list calls DAO'() {
        when: 'the list method is called'
        List<Contact> actualContacts = contactResource.listContact()

        then: 'it passes the contact to the DAO and does nothing else'
        1 * mockContactDAO.list() >> [expectedContact]
        0 * _
        actualContacts == [expectedContact]
    }

    def 'search calls DAO'() {
        given: 'a name to search by'
        String name = 'Kyle'
        SearchCommand searchCommand = new SearchCommand(firstName: name)

        when: 'the search method is called'
        List<Contact> actualContacts = contactResource.search(searchCommand)

        then: 'it passes the name to the DAO and returns the result'
        1 * mockContactDAO.findByFirstName(name) >> [expectedContact]
        0 * _
        actualContacts == [expectedContact]
    }
}
