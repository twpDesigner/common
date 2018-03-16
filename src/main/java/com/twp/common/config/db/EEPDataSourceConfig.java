//package com.twp.common.config.db;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.wall.WallConfig;
//import com.alibaba.druid.wall.WallFilter;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Configuration
//public class EEPDataSourceConfig {
//
//    @Primary
//    @Bean("eepDatasource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource druidDataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//
//        List<Filter> filterList=new ArrayList<>();
//        filterList.add(wallFilter());
//        datasource.setProxyFilters(filterList);
//
//        return datasource;
//    }
//    @Bean
//    public WallFilter wallFilter(){
//        WallFilter wallFilter=new WallFilter();
//        wallFilter.setConfig(wallConfig());
//        return  wallFilter;
//    }
//    @Bean
//    public WallConfig wallConfig(){
//        WallConfig config =new WallConfig();
//        config.setMultiStatementAllow(true);//允许一次执行多条语句
//        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
//        return config;
//    }
//
////    @Bean(name = "xgmDS")
////    @Qualifier("xgmDS")
////    @ConfigurationProperties(prefix="spring.xgm.datasource")
////    public DataSource secondaryDataSource(){
////        return DataSourceBuilder.create().build();
////    }
//}
