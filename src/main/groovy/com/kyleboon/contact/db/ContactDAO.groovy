package com.kyleboon.contact.db

import com.kyleboon.contact.core.Contact
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import org.hibernate.criterion.MatchMode
import org.hibernate.criterion.Restrictions

/**
 * User: kboon
 * Date: 11/19/12
 */
class ContactDAO extends AbstractDAO<Contact> {

    ContactDAO(SessionFactory factory) {
        super(factory)
    }

    Contact saveOrUpdate(Contact contact) {
        return persist(contact)
    }

    Contact findById(Long id) {
        return currentSession()
                .createCriteria(Contact)
                .add(Restrictions.eq('id', id))
                .uniqueResult() as Contact
    }

    List<Contact> list() {
        return currentSession()
                .createCriteria(Contact).list() as List<Contact>
    }

    List<Contact> findByFirstName(String firstName) {
        return currentSession()
                .createCriteria(Contact)
                .add(Restrictions.ilike('firstName', firstName, MatchMode.ANYWHERE))
                .list() as List<Contact>
    }
}
