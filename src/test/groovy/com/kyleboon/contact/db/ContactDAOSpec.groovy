package com.kyleboon.contact.db

import com.kyleboon.contact.core.Address
import com.kyleboon.contact.core.Contact

class ContactDAOSpec extends DAOTestHelper<ContactDAO> {
    ContactDAO buildDAO() {
        return new ContactDAO(sessionFactory)
    }

    List<Class<?>> getEntities() {
        return [Contact, Address]
    }

    def 'persists and retrieves a contact'() {
        given: 'an unpersisted contact with an address'
        Address address = new Address(
                address1:'15 South 5th Street',
                address2:'',
                city:'Minneapolis',
                state:'MN',
                county:'Hennepin',
                zipCode:'55402'
        )

        Contact contact = aContact()
        contact.address = address

        when: 'it is persisted'
        Contact persistedContact = dao.saveOrUpdate(contact)
        Contact retreivedContact = dao.findById(persistedContact.id)

        then: 'it is identical to the retrived version'
        assert persistedContact == retreivedContact
    }

    def 'finds a list of contacts'() {
        given: '2 contacts'
        Contact contact = aContact()

        Contact contact2 = aContact()

        dao.saveOrUpdate(contact)
        dao.saveOrUpdate(contact2)

        when: 'we get the list of all contact'
        List<Contact> contactList = dao.list()

        then: 'the list has 2 contacts in it'
        assert contactList.size() == 2
    }

    def 'finds contact by first name'() {
        given: 'a contact with the first name of Kyle'
        Contact contact = aContact()

        dao.saveOrUpdate(contact)

        when: 'we search for Kyle'
        List<Contact> contactList = dao.findByFirstName(contact.firstName)

        then: 'we find him'
        assert contactList.size() == 1
    }

    Contact aContact() {
        return new Contact(
                firstName:'Kyle',
                lastName:'Boon',
                jobTitle:'developer',
                phoneNumber:'999-999-9999',
                address: null
        )
    }



}
