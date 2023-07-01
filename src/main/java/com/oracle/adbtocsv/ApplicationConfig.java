package com.oracle.adbtocsv;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.pool.OracleDataSource;

import java.util.Properties;

@Configuration
@MapperScan("com.oracle.adbtocsv.mapper")
public class ApplicationConfig {

	@Value("${spring.datasource.url}")
	String url;

	@Value("${spring.datasource.username}")
	String username;

	@Value("${spring.datasource.password}")
	String password;

	@Value("${walletPath}")
	String walletPath;

	@Bean
	public DataSource dataSource() {

		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
			Properties props = new Properties();
			System.getProperty("oracle.net.wallet_location");
			props.put("oracle.net.wallet_location",
					"(source=(method=file)(method_data=(directory=" + walletPath + ")))");
			dataSource.setConnectionProperties(props);
			dataSource.setURL(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sessionFactory;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setFetchSize(20000);
		return jdbcTemplate;
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactoryBean sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory.getObject());
	}

}
