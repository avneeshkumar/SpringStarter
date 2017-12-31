package ai.edbox.SpringStarter.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ai.edbox.coreutils.db.DataSourceLocator;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ai.edbox.SpringStarter")
public class AppConfig {
	
	@Bean(name = "dslocator")
	public DataSourceLocator getDsLocator() {
		return new DataSourceLocator();
	}
	
	@Bean(name = "dataSource")
	public DataSource getEdboxMasterDs() {
		DataSource dataSource = getDsLocator().getEdboxMasterDs();
		return dataSource;
	}
}
