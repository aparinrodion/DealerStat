package org.leverx.dealerstat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatabaseInitializer {
    @Autowired
    public void loadData(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator =
                new ResourceDatabasePopulator(false, false,
                        "UTF-8", new ClassPathResource("database/init_tables.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
