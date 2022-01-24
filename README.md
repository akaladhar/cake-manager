# Cake-manager

This is a Spring boot based web application which serves cake manager end points.

## End points

This application has 5 end points.

- Root Url '/' returns lits of cakes in HTML content (Served by CakeController.class)
- '/addnew' returns HTML form to add new cake. (Served by CakeController.class)
- Post to '/cakes' end point creates a new cake. (Served by CakeRestController.class) 
  Input json to create cake is  
  ```json
  {
    "title": "Cake Title",
    "desc":  "Cake Description",
    "image": "URL to image"
  }
  ```
- Get on '/cakes' returns list of cakes in the system in JSON format. (Served by CakeRestController.class)
- '/swagger-ui.html' returns Swagger document. (Served by Swagger library)

## How to build and run without Docker

Download the code using https://github.com/akaladhar/cake-manager.git.
- To build the jar file run ```gradle build ```
- To run it locally run ```gradle bootRun```, this will host application on localhost:8080.

## How to build and run Docker image

### Pre-requisites

- Docker must be installed.
- Docker daemon must be running.

### How to build 

Download the code using https://github.com/akaladhar/cake-manager.git.

 - ```gradle build docker``` builds docker image.
 - To run Docker image, run ```docker run -p 8080:8080 -t wrcl-cakemgr/cake-manager```



