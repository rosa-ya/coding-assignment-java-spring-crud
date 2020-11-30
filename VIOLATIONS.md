Provide a list of violations in the current implementation

* ProductController.java

    1. There is no need for constructor , getter and setter for "productRepository"
        
    2. productRepository.findById(id).get()
    
       Optional value should only be accessed after calling isPresent().Optional value can hold either a value or not. The value held in the Optional can be accessed using the get() method, but it will throw a NoSuchElementException if there is no value present. To avoid the exception, calling the isPresent() or ! isEmpty() method should always be done before any call to get().
       Alternatively, note that other methods such as orElse(...), orElseGet(...) or orElseThrow(...) can be used to specify what to do with an empty Optional.
       
    3. methods "newProduct", "updateProduct", "deleteProduct" 
    
        make this methods "public" , "@RequestMapping" methods should be "public".
       
    4. Product.java class is persistent object (@Entity)
    
        Persistent entities should not be used as arguments of "@RequestMapping" methods.if a persistent object is used as an argument of a method annotated with @RequestMapping, it's possible from a specially crafted user input, to change the content of unexpected fields into the database,For this reason, using @Entity objects as arguments of methods annotated with @RequestMapping should be avoided.
        
    5. Provide the parametrized type for generic and also remove usage of generic wildcard type
        
        Generic types shouldn't be used raw (without type parameters) in variable declarations or return values.
        
    6. Define and throw a dedicated exception instead of using a generic one
       
       Using such generic exceptions as Exception prevents calling methods from handling true, system-generated exceptions differently than application-generated errors.
        
    7. "deleteProduct" method  
        For this methods use "@DeleteMapping" instead of "@PostMapping"    
     
    8. "findAll" , "findById"    
        For this methods use "@GetMapping" instead of "@PostMapping" 
     
       
      
* Product.java

    1. GenerationType.IDENTITY 
    
        For generating Id , it's better to use IDENTITY.
        AUTO is the default strategy for @GeneratedValue. If we just want to have a primary key, we can use the AUTO strategy.The IDENTITY strategy relies on the database auto-increment column. The database generates the primary key after each insert operation. JPA assigns the primary key value after performing the insert operation or upon transaction commit



* ProductServiceApplicationTests.java

    1. adding at least one assertion to this test case
    
    
** There is no comments in code