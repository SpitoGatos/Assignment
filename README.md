Project Spitogatos Assignment
Java : Version 17
Framework : Spring Boot 
ORM : Hibernate
Build automation : gradle
IDE : intellij IDEA

Thing I didn’t do : Front end , Junit Test 

Questions 1,2 
I didn’t use a database for simplicity and speed of development. 
I chose to implement JWT for its simplicity, security, and versatility. 
Its widespread adoption across various platforms and its support for stateless authentication(RESTFUL)
make it an excellent choice for modern web applications, APIs, and microservices architectures, streamlining development and enhancing security.

Questions 3,4,5

From the Java Persistence API (JPA) and Java Bean Validation (Bean Validation) frameworks. 
@Id: Marks the field as the primary key of the entity.
@GeneratedValue: Specifies the strategy used for value generation for the primary key. In this case, it indicates that the primary key values are generated by the database (auto-increment in most databases).
@NotBlank: Specifies that the annotated string must not be null and must contain at least one non-whitespace character. Also, it provides a custom error message if validation fails.
@Pattern: Validates that the annotated string matches the specified regular expression pattern. It ensures that the area field contains one of the listed cities.
@Min: Validates that the annotated numeric value is greater than or equal to the specified minimum value.
Java Bean Validation (Bean Validation) for server-side validation. It employs the Validator interface from the Bean Validation API to validate the real estate advertisement object against specified constraints. Additionally, it utilizes Spring Framework's ResponseEntity class to handle HTTP responses and HttpStatus enum to define the HTTP status codes.


Questions 6,7
For those we utilized a get method that also checks the user before pulling the data.

Questions 8,9 
Similar to 6,7