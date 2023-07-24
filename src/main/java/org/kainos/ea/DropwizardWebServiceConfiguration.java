package org.kainos.ea;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardWebServiceConfiguration extends Configuration {
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger() {
        swagger.setResourcePackage("org.kainos.ea.resources");
        String[] schemes = {"http", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
}
