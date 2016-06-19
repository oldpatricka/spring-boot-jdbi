package hellojdbi;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.spring.DBIFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
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
