package com.kyleboon.contact

import com.kyleboon.contact.db.ContactDAO
import com.kyleboon.contact.healthchecks.MySQLHealthCheck
import com.kyleboon.contact.resources.ContactResource
import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import com.kyleboon.contact.core.Contact
import com.kyleboon.contact.core.Address

/**
 * User: kboon
 * Date: 11/14/12
 */
class ContactsService extends Application<ContactsConfiguration> {
    public static void main(String[] args) throws Exception {
        new ContactsService().run(args)
    }

    HibernateBundle<ContactsConfiguration> hibernateBundle =
        new HibernateBundle<ContactsConfiguration>(Contact, Address) {
            @Override
            public DataSourceFactory getDataSourceFactory(ContactsConfiguration configuration) {
                return configuration.database
            }
        }

    MigrationsBundle<ContactsConfiguration> migrationsBundle =
        new MigrationsBundle<ContactsConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ContactsConfiguration configuration) {
                return configuration.database
            }
        }

    @Override
    public void initialize(Bootstrap<ContactsConfiguration> bootstrap) {
        bootstrap.with {
            addBundle migrationsBundle
            addBundle hibernateBundle
            addBundle(new AssetsBundle('/apidocs/'))
            addBundle(new AssetsBundle('/swagger-ui-1.1.0/', '/swagger'))

        }
    }

    @Override
    public void run(ContactsConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        ContactDAO contactDAO = new ContactDAO(hibernateBundle.sessionFactory)

        environment.jersey().register(new ContactResource(contactDAO))
        environment.healthChecks().register('sessionFactory', new MySQLHealthCheck(hibernateBundle.sessionFactory))
    }
}
