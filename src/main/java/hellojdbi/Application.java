package hellojdbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.spring.DBIFactoryBean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public ExampleDao exampleDao(DBI dbi) {
        return dbi.onDemand(ExampleDao.class);
    }

    @Autowired
    @Bean
    public DBI dbi(DataSource dataSource) {
        synchronized (DBI.class) {
            return new DBI(dataSource);
        }
    }

    @Autowired
    @Bean
    public DBIFactoryBean dbiFactory(DataSource dataSource) {
        DBIFactoryBean dbiFactoryBean = new DBIFactoryBean();
        dbiFactoryBean.setDataSource(dataSource);
        return dbiFactoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
