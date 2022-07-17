package io.wybis.controllers;

import lombok.extern.slf4j.Slf4j;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j
@Path("/")
public class DefaultController {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive...";
    }

    @GET
    @Path("test-log")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> testLog() {
        log.error("Test error log...");
        log.warn("Test warn log...");
        log.info("Test info log...");
        log.debug("Test debug log...");
        log.trace("Test trace log...");
        return Map.of("message", "Successfully added test logs");
    }

    @ConfigProperty(name = "quarkus.log.file.enable")
    boolean fileLogState;

    @ConfigProperty(name = "quarkus.log.handler.gelf.enabled")
    boolean greyLogState;

    @GET
    @Path("toggle-log")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> toggleLog() {
         return Map.of("FileLogState", fileLogState, "GreyLogState", greyLogState);
    }
}