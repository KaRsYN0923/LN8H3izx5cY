// 代码生成时间: 2025-09-21 19:59:17
import io.dropwizard.Application;
import io.dropwizard.configuration.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.View;
import io.dropwizard.views.mustache.MustacheViewRenderer;
import io.dropwizard.views.mustache.MustacheTemplateRenderer;

import java.net.URL;
import java.net.MalformedURLException;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/validate")
public class URLValidatorResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response validateURL(@QueryParam("url") String url) {
        try {
            validateURLFormat(url);
            return Response.ok("URL is valid.").build();
        } catch (MalformedURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL format.").build();
        }
    }

    private void validateURLFormat(String url) throws MalformedURLException {
        new URL(url); // This will throw MalformedURLException if the URL is invalid
    }
}

public class URLValidatorApplication extends Application<Configuration> {

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do here
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new URLValidatorResource());
        environment.getObjectMapper().findAndRegisterModules();
    }

    public static void main(String[] args) throws Exception {
        new URLValidatorApplication().run(args);
    }
}
