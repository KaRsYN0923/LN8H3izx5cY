// 代码生成时间: 2025-09-19 22:04:30
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.health.HealthCheck;
import io.dropwizard.health.HealthCheckRegistry;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UnitService extends Application<UnitServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<UnitServiceConfiguration> bootstrap) {
        // nothing to do here
    }

    @Override
    public void run(UnitServiceConfiguration configuration, Environment environment) {
        final JerseyEnvironment jersey = environment.jersey();
        jersey.register(new UnitResource());
        final HealthCheckRegistry healthCheckRegistry = environment.healthChecks();
        healthCheckRegistry.register("example", new ExampleHealthCheck());
    }

    public static class UnitResource {
        @GET
        @Path("/ping")
        @Produces(MediaType.TEXT_PLAIN)
        public String ping() {
            return "pong";
        }
    }

    public static class ExampleHealthCheck implements HealthCheck {
        @Override
        public Result check() {
            // Implement your health check logic here
            return Result.healthy("I'm healthy!");
        }
    }

    // Unit test class
    public static class UnitTest {
        @ClassRule
        public static final DropwizardAppRule<UnitServiceConfiguration> RULE =
            new DropwizardAppRule<>(UnitService.class, "config.yml");

        @Test
        public void testPing() {
            assertTrue(RULE.client().target("http://localhost:8080/ping").request().get(String.class).equals("pong"));
        }
    }
}

/**
 * Configuration class for UnitService.
 */
class UnitServiceConfiguration extends Configuration {
    // Add configuration properties here
}
