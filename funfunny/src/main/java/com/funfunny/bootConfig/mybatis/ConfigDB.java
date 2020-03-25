package com.funfunny.bootConfig.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//@MapperScan(
//		basePackages = "com.funfunny.**.*mapper", 
//		sqlSessionFactoryRef = "sqlSessionFactory"
//		)
public class ConfigDB {
	

	@Bean
	@ConfigurationProperties("spring.datasource")
	public HikariDataSource dataSource() {
		return new HikariDataSource();
	}

//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(this.dataSource());
//	    return factoryBean.getObject();
//	}
//	
//
//	@Bean
//	public SqlSessionTemplate sqlSession() throws Exception {
//		return new SqlSessionTemplate(this.sqlSessionFactory()) ;
//	}
//	
	
}
