package com.techatpark.scubebird.core.implementation.util;

import com.techatpark.scubebird.core.crawler.Crawler;
import com.techatpark.scubebird.core.exception.ScubeException;
import com.techatpark.scubebird.core.implementation.mapper.Mapper;
import com.techatpark.scubebird.core.implementation.mapper.java.JavaOrmMapper;
import com.techatpark.scubebird.core.model.DaoProject;
import com.techatpark.scubebird.core.model.Schema;
import com.techatpark.scubebird.crawler.postgres.PostgresCrawler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class ScubeUtilTest {

    @Test
    void writeCode() throws IOException, ClassNotFoundException, ScubeException, SQLException {
        DaoProject daoProject = new DaoProject();

        daoProject.setName("Sakila");
        daoProject.setUrl("jdbc:postgresql://localhost:5432/sakila");
        daoProject.setUserName("postgres");
        daoProject.setPassword("postgres");
        daoProject.setSchemaName("sakila");
        daoProject.setTablePatterns(Arrays.asList("actor"));

        daoProject.setOnline(true);

        daoProject.setBeanIdentifier("model");
        daoProject.setDaoIdentifier("dao");
        daoProject.setDaoSuffix("");

        daoProject.setMethodSpecification(Arrays.asList("DeleteByEntity", "DeleteByPK", "Without", "GetAll", "GetByEntity", "GetByPK", "GetByPKExceptHighest", "GetByPKUniqueKeys",
                "InsertByEntiy", "IsExisting", "MViewRefresh", "SaveByPK", "UpdateByEntiy", "UpdateByPK"));

        daoProject.setRootPackage("org.example");

        daoProject.setSrcFolder("/home/haripriya/Official/java-oracle-jdbc/target/generated-sources/sakila-postgres");
        daoProject.setCleanSource(true);

        Crawler crawler = new PostgresCrawler(daoProject);
        Mapper mapper = new JavaOrmMapper();
        daoProject.setOrm(mapper.getOrm(daoProject,crawler));

        ScubeUtil.writeCode(daoProject);
        Assertions.assertNotNull(daoProject);
    }
}