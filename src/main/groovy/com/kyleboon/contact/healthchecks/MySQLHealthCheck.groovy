package com.kyleboon.contact.healthchecks

import com.codahale.metrics.health.HealthCheck
import org.hibernate.SessionFactory
import com.codahale.metrics.health.HealthCheck.Result

import static com.codahale.metrics.health.HealthCheck.Result.*


public class MySQLHealthCheck extends HealthCheck {
    private final SessionFactory sessionFactory

    public MySQLHealthCheck(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
    }

    @Override
    protected Result check() {
        if (sessionFactory.closed) {
            return unhealthy('Session Factory is Closed!')

        }

        return healthy()
    }
}
