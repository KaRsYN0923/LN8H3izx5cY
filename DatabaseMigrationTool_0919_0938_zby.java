// 代码生成时间: 2025-09-19 09:38:30
import io.dropwizard.Application;
import io.dropwizard.configuration.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Configuration;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DatabaseMigrationTool extends Application<DatabaseMigrationToolConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseMigrationTool.class);

    public static void main(String[] args) throws Exception {
        new DatabaseMigrationTool().run(args);
    }

    @Override
    public void initialize(Bootstrap<DatabaseMigrationToolConfiguration> bootstrap) {
        // Register the migrations bundle
        bootstrap.addBundle(new MigrationsBundle<DatabaseMigrationToolConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DatabaseMigrationToolConfiguration configuration) {
                // Return the data source factory from the configuration
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(DatabaseMigrationToolConfiguration configuration, Environment environment) throws Exception {
        // Create a Liquibase instance
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
        Database database = DatabaseFactory.getInstance().open(configuration.getDataSourceFactory());
        Liquibase liquibase = new Liquibase("db/changelog/master.xml", resourceAccessor, database);

        // Execute the database migration
        try {
            liquibase.update("dropwizard");
            logger.info("Database migration completed successfully.");
        } catch (Exception e) {
            logger.error("Error during database migration", e);
            throw e;
        } finally {
            // Close the database connection
            database.close();
        }
    }
}
