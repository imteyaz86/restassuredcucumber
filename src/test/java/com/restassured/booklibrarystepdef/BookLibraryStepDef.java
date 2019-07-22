package com.restassured.booklibrarystepdef;

import java.util.HashMap;
import java.util.Map;
import com.restassured.booklibrarysteplib.BookLibraryStepLib;
import com.restassured.commonutils.Generics;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class BookLibraryStepDef {
    
    Generics generic = new Generics();
    BookLibraryStepLib bookStepLib = new BookLibraryStepLib();
    RequestSpecification request;
    ValidatableResponse response;
    Map<String, Object> obj;
    String id, json;
    String addedBookId;
    
    
    @Given("book details to be added is given")
    public void book_details_to_be_added_is_given() throws Throwable { 
       
        request = bookStepLib.setContentTypeJsonForRequest();
     
    }

    @When("post operation is done")
    public void post_operation_is_done() throws Throwable {
        String payLoad;
        payLoad = generic.getPayLoad("booklibraryjsonpayload/addbook");
        payLoad = generic.updateJson(payLoad, "isbn");
        response =  request.body(payLoad).when().post(generic.getResource("BookLibrary", "AddBook_Post")).then();
        
    }

    @Then("book should be added and status code is {int}")
    public void book_should_be_added_and_status_code_is(Integer int1) throws Throwable  {
        addedBookId = response.assertThat().statusCode(int1).and().body("Msg", equalTo("successfully added")).and().extract()
        .jsonPath().get("ID");
        System.out.println("Book id is"+addedBookId);
        
    }

    @Given("id of an existing book")
    public void id_of_an_existing_book() throws Throwable {
        System.out.println("Given2" +addedBookId);
        request =given().contentType(ContentType.JSON).param("ID", addedBookId);
        
    }

    @When("book is searched using  get operation")
    public void book_is_searched_using_post_operation() throws Throwable {
     response=  request.when().get(generic.getResource("BookLibrary", "RetrieveBook_Get")).then();
        
    }

    @Then("status code is {int}")
    public void status_code_is(Integer int1) {
        response.assertThat().statusCode(int1).and().extract().jsonPath().get("isbn").equals(json);
        
    }

    
    @Given("id of an existing book added")
    public void id_of_an_existing_book_added() throws Throwable {
        
        obj = new HashMap<>();
        obj.put("ID", id);
        request = bookStepLib.setContentTypeJsonForRequest();
        
    }
    @When("book is deleted using post operation")
    public void book_is_deleted_using_post_operation() throws Throwable {
       
       response= request.body(obj).when().post(generic.getResource("BookLibrary", "DeleteBook_Post")).then(); 
    }

    @Then("book should be deleted and status code is {int}")
    public void book_should_be_deleted_and_status_code_is(Integer int1) {
        response.assertThat().statusCode(int1).and().body("msg", equalTo("book is successfully deleted"));
        
    }


    
    
    

}
