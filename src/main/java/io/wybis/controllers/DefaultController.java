package io.wybis.controllers;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Slf4j
@Path("/")
public class DefaultController {

    private final Template index;

    @ConfigProperty(name = "app.name")
    String appName;

    public DefaultController(Template index) {
        this.index = requireNonNull(index, "index template is required");
    }

    @GET
    @Path("index")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return index.data("appName", this.appName).data("appMode", io.quarkus.runtime.LaunchMode.current());
    }

    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> ping() {
        return Map.of("Ping", "Pong");
    }

    @GET
    @Path("app-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> appInfo() {
        Map<String, Object> appInfo = Map.of(
                "mode", io.quarkus.runtime.LaunchMode.current(),
                "name", appName
        );
        return appInfo;
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
        Map<String, Object> map = new HashMap<>();
        map.put("oldFileLogState", fileLogState);
        map.put("oldGrayLogState", greyLogState);
        return map;
    }
}