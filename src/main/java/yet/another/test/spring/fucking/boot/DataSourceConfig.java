package yet.another.test.spring.fucking.boot;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author nexor
 */
@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSourceBuilder.url("jdbc:sqlserver://NOTE-DELL:1433");
		dataSourceBuilder.username("sa");
		dataSourceBuilder.password("sa");
		return dataSourceBuilder.build();
	}
}
