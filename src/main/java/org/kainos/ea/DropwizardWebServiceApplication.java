package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.controller.CapabilityController;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.BandService;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;

public class DropwizardWebServiceApplication extends Application<DropwizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardWebService";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardWebServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardWebServiceConfiguration>() {

            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardWebServiceConfiguration dropwizardWebServiceConfiguration) {
                return dropwizardWebServiceConfiguration.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropwizardWebServiceConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new BandController(new BandService(new BandDao(), new DatabaseConnector())));
        environment.jersey().register(new CapabilityController(new CapabilityService(new CapabilityDao(), new DatabaseConnector())));
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(), new DatabaseConnector())));
    }

}