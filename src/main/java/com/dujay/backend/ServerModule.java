package com.dujay.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerModule extends AbstractModule {

    private final Logger logger = LoggerFactory.getLogger(ServerModule.class);

    @Override
    protected void configure() {
        bind(SparkServer.class);
        bind(JsonTransformer.class);
    }

    @Provides
    ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Provides
    HikariDataSource hikariDataSource() {
        final String username = "admin";
        final String password = "admin";
        final String database = "tophat";

        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setJdbcUrl(String.format("jdbc:postgresql:%s", database));
        return hikariDataSource;
    }
}
