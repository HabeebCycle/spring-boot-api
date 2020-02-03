# CRUD Operations with Spring Boot and MongoDB

Basic create, read, update and delete API building using spring-boot and mongoDB Atlas.

```
Dependecies

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## Query in Spring Data MongoDB
With Spring data mongodb, documents can be queried with Query and Criteria classes, auto-generated query methods and
 JSON queries.
 
 ### Documents Query
 One of the more common ways to query MongoDB with Spring Data is by making use of the Query and Criteria 
 classes – which very closely mirror native operators.
 
*  #### 'Is' Operator
   This is simply a criterion using equality – let's see how it works.
In the following example – we're looking for authors named Habeeb from the document below:

```
[
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581907"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Habeeb",
        "age" : 34
    },
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581908"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Adedayo",
        "age" : 55
    }
}
]
```
    
With query below

    Query query = new Query();
    query.addCriteria(Criteria.where("name").is("Habeeb"));
    List<Author> authors = mongoTemplate.find(query, Author.class);
returns as expected:

    {
        "_id" : ObjectId("55c0e5e5511f0a164a581907"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Habeeb",
        "age" : 34
    }
## Running the SpringBoot application

```cd``` into the project directory and run the following command to create a ```.jar``` file of the project:

```shell
mvn clean install
```

This will create a ```.jar``` file in the ```target``` directory, inside the project directory. Now to run the project, run the following command from the same project directory:

```shell
java -jar target/<name_of_jar_file>.jar
```

You should now be seeing the output in the terminal.