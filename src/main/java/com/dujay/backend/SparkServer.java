package com.dujay.backend;

import com.google.inject.Inject;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.time.LocalDateTime;
import java.util.HashMap;

import static spark.Spark.*;

public class SparkServer {

    private final Logger logger = LoggerFactory.getLogger(SparkServer.class);

    private final JsonTransformer jsonTransformer;
    private final HikariDataSource hikariDataSource;

    @Inject
    public SparkServer(JsonTransformer jsonTransformer, HikariDataSource hikariDataSource) {
        this.jsonTransformer = jsonTransformer;
        this.hikariDataSource = hikariDataSource;
    }

    void start() {
        logger.info(">>>start()");

        port(8080);

        before("*", (req, res) -> {
            res.type("application/json");
        });

        get("/now", (req, res) -> {
            logger.info(">>>now()");
            return new HashMap<String, Object>() {{
                put("now", LocalDateTime.now());
            }};
        }, this.jsonTransformer);

        logger.info("<<<start()");
    }

    void stop() {
        logger.info(">>>stop()");

        Spark.stop();

        logger.info("<<<stop()");
    }
}
