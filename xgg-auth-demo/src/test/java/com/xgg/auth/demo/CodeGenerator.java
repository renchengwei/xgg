package com.xgg.auth.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: renchengwei
 * @Date: 2019-08-01
 * @Description: TODO
 */
public class CodeGenerator {

    private static String projectPath;

    public static String module() {
        return "/xgg-auth-demo";
    }

    public static void main(String[] args) {
        projectPath = System.getProperty("user.dir");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        globalConfig(mpg);
        dataSourceConfig(mpg);
        packageConfig(mpg);
        injectionConfig(mpg);
        templateConfig(mpg);
        strategyConfig(mpg);
        mpg.execute();
    }

    /**
     * @Author renchengwei
     * @Date 2019/8/2
     * @Description 全局配置
     */
    private static void globalConfig(AutoGenerator mpg) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath + module() + "/src/main/java")
                .setAuthor("renchengwei")
                .setOpen(false)
                .setFileOverride(true)
                .setEntityName("%sPO")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setControllerName("%sController")
                .setIdType(IdType.AUTO)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setEnableCache(true);
        mpg.setGlobalConfig(gc);
    }

    private static void dataSourceConfig(AutoGenerator mpg) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("mytest")
                .setPassword("123456")
                .setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
    }
    private static void packageConfig(AutoGenerator mpg) {
        // 数据源配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.xgg.auth.demo")
                .setEntity("pojo.po")
                .setMapper("dao")
                .setXml(projectPath + module() + "/src/main/resources/mapper/");
        mpg.setPackageInfo(pc);
    }
    // 自定义配置
    private static void injectionConfig(AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + module() + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    private static void templateConfig(AutoGenerator mpg) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setXml(null)
                .setEntity("/templates/entity.java")
                .setController("/templates/controller.java")
                .setMapper("/templates/mapper.java")
                .setService("/templates/service.java")
                .setServiceImpl("/templates/serviceImpl.java");
        mpg.setTemplate(templateConfig);
    }

    private static void strategyConfig(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setLogicDeleteFieldName("is_deleted")
                .setControllerMappingHyphenStyle(false);
        mpg.setStrategy(strategy);
    }
}
