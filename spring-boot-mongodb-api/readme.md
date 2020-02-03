# CRUD Operations with Spring Boot and MongoDB

Basic create, read, update and delete API building using spring-boot and mongoDB Atlas.

Also used manual relationship between two collections Author and Book with One-To-Many relationship.
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
A database with 2 collections (Author and Book) were created in MongoDB Atlas and connection string was configure in properties.yml file

## Query in Spring Data MongoDB
With Spring data mongodb, documents can be queried with Query and Criteria classes, auto-generated query methods and
 JSON queries. Author collection will be used below to explain different ways to use spring data mongodb.
 
 ### Documents Query
 One of the more common ways to query MongoDB with Spring Data is by making use of the Query and Criteria 
 classes – which very closely mirror native operators.
 
*  #### 'Is' Operator
   This is simply a criterion using equality – let's see how it works.
In the following example – we're looking for authors named Habeeb from the documents below:

```
[
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581907"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Habeeb",
        "age" : 28
    },
    {
         "_id" : ObjectId("55c0e5e5511f0a164a581907"),
         "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
         "name" : "Akinbode",
         "age" : 45
     },
     {
         "_id" : ObjectId("55c0e5e5511f0a164a581908"),
         "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
         "name" : "Adedoyin",
         "age" : 33
     },
     {
         "_id" : ObjectId("55c0e5e5511f0a164a581909"),
         "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
         "name" : "Soyinka",
         "age" : 75
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
        "age" : 28
    }
    
* 	#### Regex
     A more flexible and powerful type of query is the regex. This creates a criterion using a MongoDB
      $regex that returns all records suitable for this regexp for this field.
      It works similar to startingWith, endingWith operations – 
      let's look at an example. We're now looking for all authors that 
      have names starting with A from the Author collection below:
```
[
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581907"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Habeeb",
        "age" : 28
    },
    {
        "_id" : ObjectId("55c0e5e5511f0a164a436541"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Akinbode",
        "age" : 45
    },
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581908"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Adedoyin",
        "age" : 33
    },
    {
        "_id" : ObjectId("55c0e5e5511f0a164a581909"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Soyinka",
        "age" : 75
    }
]
```
With the query below:

	Query query = new Query();
	query.addCriteria(Criteria.where("name").regex("^A"));
	List<Author> authors = mongoTemplate.find(query, Author.class);

This runs and returns 2 records:

	[
       {
          "_id" : ObjectId("55c0e5e5511f0a164a436541"),
          "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
          "name" : "Akinbode",
          "age" : 45
       },
       {
          "_id" : ObjectId("55c0e5e5511f0a164a581908"),
          "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
          "name" : "Adedoyin",
          "age" : 33
       }
    ]

Here's another quick example, this time looking for all authors that have names ending with a

    Query query = new Query();
    query.addCriteria(Criteria.where("name").regex("a$"));
    List<Author> authors = mongoTemplate.find(query, Author.class);
    
and returns

    {
        "_id" : ObjectId("55c0e5e5511f0a164a581909"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Soyinka",
        "age" : 75
    }

*  #### lt and gt operators
These operators create a criterion using the $lt (less than) operator and $gt (greater than). 
Let's have a quick look at an example – we're looking for all authors with age between 20 and 40.

    Query query = new Query();
    query.addCriteria(Criteria.where("age").lt(40).gt(20));
    List<Author> authors = mongoTemplate.find(query, Author.class);
and returns the result – all authors whose age is greater than 20 and less than 40

    {
        "_id" : ObjectId("55c0e5e5511f0a164a581908"),
        "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
        "name" : "Adedoyin",
        "age" : 33
    }
    
*   #### Sort
 Sort is used to specify a sort order for the results.
    The example below returns all authors sorted by age in ascending order.
    
    Query query = new Query();
    query.with(new Sort(Sort.Direction.ASC, "age"));
    List<Author> authors = mongoTemplate.find(query, Author.class);
    
    [
        {
            "_id" : ObjectId("55c0e5e5511f0a164a581907"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Habeeb",
            "age" : 28
        },
        {
            "_id" : ObjectId("55c0e5e5511f0a164a581908"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Adedoyin",
            "age" : 33
        },
        {
            "_id" : ObjectId("55c0e5e5511f0a164a436541"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Akinbode",
            "age" : 45
        },
        {
            "_id" : ObjectId("55c0e5e5511f0a164a581909"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Soyinka",
            "age" : 75
        }
    ]
    
*   #### Pageable
 Let's look at a quick example using pagination. Using the collection document above:
    
    final Pageable pageableRequest = PageRequest.of(0, 2);
    Query query = new Query();
    query.with(pageableRequest);
 
    [
        {
            "_id" : ObjectId("55c0e5e5511f0a164a581907"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Habeeb",
            "age" : 28
        },
        {
            "_id" : ObjectId("55c0e5e5511f0a164a581908"),
            "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
            "name" : "Adedoyin",
            "age" : 33
        }
    ]
equivalent to:

    Query query = new Query();
    query.with(Sort.by(Sort.Order.asc("age"))).limit(2);
    List<Author> authors = mongoTemplate.find(query, Author.class)
    
    

### Generated Query Methods
Let's now explore the more common type of query that Spring Data usually provides – auto-generated queries out of method names.
The only thing we need to do to leverage these kinds of queries is to declare the method on the repository interface:

    @Repository
    public interface AuthorRepository extends MongoRepository<Author, String>{
        ...
    }
* #### FindByX
  We'll start simple, by exploring the findBy type of query – in this case, find by name:
    
        List<User> findByName(String name); 
        
        Example
        List<Author> authors = authorRepository.findByName("Habeeb"); //Same result as 'Is operator'
        
*  #### StartingWith and endingWith
    Starts and ends with are of course less powerful, but nevertheless quite useful – 
especially if we don't have to actually implement them. Here's a quick example of how the operations would look like:

        List<Author> findByNameStartingWith(String regexp);
        List<Author> findByNameEndingWith(String regexp);
        
        Examples:
        
        List<Author> authors = authorRepository.findByNameStartingWith("A"); //results as Regex section ^A
        List<Author> authors = authorRepository.findByNameEndingWith("a"); //results as Regex section a$
* #### Between
  This is like less than with greater than and will return all authors with age between ageGT and ageLT
  
        List<Author> findByAgeBetween(int ageGT, int ageLT);
        
        Example:
        List<Author> authors = authorRepository.findByAgeBetween(20, 40);
        
* #### Like and OrderBy
  Let's have a look at a more advanced example this time – combining two types of modifiers for the generated query.  
  We're going to be looking for all authors that have names containing the letter A and we're also going to order the results by age, in ascending order
  
        List<Author> authors = authorRepository.findByNameLikeOrderByAgeAsc("A");
        
        [
            {
                "_id" : ObjectId("55c0e5e5511f0a164a581908"),
                "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
                "name" : "Adedoyin",
                "age" : 33
            },
            {
                "_id" : ObjectId("55c0e5e5511f0a164a436541"),
                "_class" : "com.habeebcycle.springbootmongodbapi.model.Author",
                "name" : "Akinbode",
                "age" : 45
            }
        ]
        
### JSON Query Methods
If we can't represent a query with the help of a method name, or criteria, we can do something more low level – use the 
```@Query``` annotation. With this annotation, we can specify a raw query – as a Mongo JSON query string

* #### FindBy
  Let's start simple and look at how we would represent a find by type of method first

        @Query("{ 'name' : ?0 }")
        List<Author> findAuthorsByName(String name);
    
    This method should return authors by name – the placeholder ?0 references the first parameter of the method.
  
* #### $regex
   Let's also look at a regex driven query – which of course produces the same result as regex section above
    
        @Query("{ 'name' : { $regex: ?0 } }")
        List<Author> findAuthorsByRegexpName(String regexp);
    The usage is also exactly the same. Examples:
        
        List<Author> authors = authorRepository.findAuthorsByRegexpName("^A");
        List<Author> authors = authorRepository.findAuthorsByRegexpName("a$");
        
* ####  $lt and $gt 
  We can use this to implement less than and greater than operations in query. Let's now implement the lt and gt query:
  
        @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
        List<Author> findAuthorsByAgeBetween(int ageGT, int ageLT);
        
  This method has 2 parameters, we're referencing each of these by index in the raw query: ?0 and ?1. Example:
  
        List<Author> authors = authorRepository.findAuthorsByAgeBetween(20, 40);
 
 
        
         
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