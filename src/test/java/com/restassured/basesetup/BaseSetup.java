package com.restassured.basesetup;

import java.io.File;

import com.restassured.commonutils.ExcelLib;
import com.restassured.commonutils.PropertyReader;

import io.restassured.RestAssured;

/**
 * Purpose : This is a base class in which before suite and after suite
 * configuration is defined
 * 
 * @author iahmad
 *
 */

public class BaseSetup {
    public static ExcelLib testDataExcel;
    public static ExcelLib resourceExcel;
    public static ExcelLib payLoadExcel;
    public static String pathSeparator;
    public static PropertyReader properties;
    private static BaseSetup baseObject = null;

    private BaseSetup() throws Throwable {

        pathSeparator = File.separator;
        testDataExcel = new ExcelLib(System.getProperty("user.dir") + pathSeparator + "src" + pathSeparator + "test"
                + pathSeparator + "resources" + pathSeparator + "data" + pathSeparator + "testData" + pathSeparator
                + "TestData.xlsx");
        resourceExcel = new ExcelLib(System.getProperty("user.dir") + pathSeparator + "src" + pathSeparator + "test"
                + pathSeparator + "resources" + pathSeparator + "data" + pathSeparator + "testData" + pathSeparator
                + "Resource.xlsx");
        payLoadExcel = new ExcelLib(System.getProperty("user.dir") + pathSeparator + "src" + pathSeparator + "test"
                + pathSeparator + "resources" + pathSeparator + "data" + pathSeparator + "testData" + pathSeparator
                + "Payload.xlsx");

        properties = new PropertyReader("utilities.properties");
        System.out.println("Url is " + properties.getPropertyValue("BaseURL"));
        RestAssured.baseURI = properties.getPropertyValue("BaseURL");
         RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.config =
        // RestAssured.config().connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse());

    }

    public static BaseSetup getInstance() throws Throwable {

        if (baseObject == null) {
            synchronized (BaseSetup.class) {
                if (baseObject == null) {
                    baseObject = new BaseSetup();// instance will be created at
                                                 // request time
                }
            }
        }
        return baseObject;
    }

    public static void killInstance() throws Throwable {

        baseObject = null;
        testDataExcel = null;
        resourceExcel = null;
        payLoadExcel = null;
        properties = null;

    }

}