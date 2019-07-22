package com.restassured.booklibrarysteplib;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BookLibraryStepLib {
    
    
    
    public RequestSpecification setContentTypeJsonForRequest() throws Throwable {
        return given().contentType(ContentType.JSON).request();
    }
    
    

}
