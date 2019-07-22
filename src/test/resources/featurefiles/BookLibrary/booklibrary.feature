#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Library Book

  @BookLibrary
  Scenario: Add a new book to library api using post
    Given book details to be added is given
    When post operation is done
    Then book should be added and status code is 200

  @BookLibrary
  Scenario: Find an existing book using get operation
    Given id of an existing book
    When book is searched using  get operation
    Then status code is 200

  @BookLibrary
  Scenario: Delete an existing book using post
    Given id of an existing book added
    When book is deleted using post operation
    Then book should be deleted and status code is 200
