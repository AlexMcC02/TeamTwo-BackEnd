package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
<<<<<<< HEAD
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.util.DatabaseConnector;
=======
>>>>>>> main

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
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardWebServiceConfiguration configuration,
                    final Environment environment) {
<<<<<<< HEAD
        environment.jersey().register(new JobRoleController(new JobRoleService(new JobRoleDao(), new DatabaseConnector())));
=======
        // TODO: implement application
>>>>>>> main
    }

}