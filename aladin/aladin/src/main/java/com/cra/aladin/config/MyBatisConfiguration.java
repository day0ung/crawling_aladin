package com.cra.aladin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Bean
	public SqlSessionFactory sqlSessionfactory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);
//		Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath:com/cra/aladin/sqls/*.xml");
//		sqlSessionFactoryBean.setMapperLocations(arrResource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mapper/db.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/sqls/*.xml"));
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return (SqlSessionFactory)sqlSessionFactoryBean.getObject();
		
	}

}
