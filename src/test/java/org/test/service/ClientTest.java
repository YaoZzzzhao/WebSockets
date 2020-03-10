package org.test.service;

//import junitparams.Parameters;
//import junitparams.naming.TestCaseName;
import org.demo.ApplicationInit;
import org.demo.service.Client;
import org.demo.service.DatabaseConn;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ApplicationInit.class)
public class ClientTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DatabaseConn databaseConn;

    @Autowired
    Client client;

    @Test
//    @Parameters()
//    @TestCaseName("getTotalTest")
    public void getTotalTest(){
        Connection con = databaseConn.con;
        String tt = client.getTotal();
        Assert.assertEquals(tt,"25");
    }
}
