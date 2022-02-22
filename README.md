
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.omarben1/listGeneration/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.omarben1/listGeneration)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
# listGeneration
listGeneration is a tool that allows you to generate a list of instances of a given class with the values of its fields are generated
randomly based on criteria you can define as annotations on the class's fields.It helps when you need a sample data 
for testing, preventing you from manually create and assign random values for each instance.

## How to use it : 
If you use [Maven](http://maven.apache.org) you can include the library to your project as a dependency :
```xml
<dependency>
  <groupId>io.github.omarben1</groupId>
  <artifactId>listGeneration</artifactId>
  <version>1.0.0</version>
</dependency>
```

 ### Sample usage : 
 Assume that we have a Student class, we want the firstName and the lastName be two strings that contain only alphabetic characters, the email be a string that has a valid email format, and the age be between 18 and 25 : 
 
 ```JAVA
 public class Student {
 
	@Criterion(data = DataType.SIMPLE_NAME)
	private String firstName;
	
	@Criterion(data = DataType.SIMPLE_NAME)
	private String lastName;
  
    @Criterion(data = DataType.EMAIL)
    private String email;
	
	@Criterion(minValue = "18", maxValue = "25")
	private int age;                             //you can use here the wrapper class instead
  
    //Getters and setters
	
}
```
Let's now generate a list of Student with a size of 5 : 
 ```JAVA
 
 public class Main {
	 public static void main(String[] args) {
         List<Product> students = ListGenerator.getList(Student.class, 5);  
         students.forEach(System.out::println);
     }
 }
 
 //Output : 
//Student [firstName=Hvbsjy,  lastName=Grzrl,    email=X4P-4@2UXZ.OKVMBQ, age=19]
//Student [firstName=Hiskiy,  lastName=Yxdkkj,   email=7BRK8@ZFWH.CC,     age=21]
//Student [firstName=Psibcgs, lastName=Dkbkrjhm, email=5QAN59@CMG-.ELR,   age=23]
//Student [firstName=Xmogz,   lastName=Thekrmad, email=EY8Z7W@ZICN.IEUV,  age=20]
//Student [firstName=Vdafd,   lastName=Nvsolj,   email=88CRM@NOBH.DYYDW,  age=21]
```

### Criterion Annotation : 
This annotation allows you to add criteria to a class's field, the supported types are all java primitive types (you can use their wrapper classes instead), as well as String and LocalDate classes.

### String Criteria : 
For fileds of type String, you can use Criterion annotation to determine : 

* The length of the word using `length` parameter.
    
    <details>
    <summary><b>Code</b></summary>
  
    ```JAVA
    calss Product{
    
      @Criterion(length = "5")
      private String name;
      //... other fields
      
      }
    ```
  </details>
  
* The type of the word using data parameter which can be : 
  * *SIMPLE_NAME* : String that contains only alphabetic characters.
    
    <details>
    <summary><b>Code</b></summary>
  
    ```JAVA
    calss Product{
    
      @Criterion(data = DataType.SIMPLE_NAME)
      private String name;
      //... other fields
      
      }
      ```
  
    </details>
    
  * *EMAIL* : String that has an email format.
    
    <details>
    <summary><b>Code</b></summary>
  
    ```JAVA
    calss Customer{
    
      @Criterion(data = DataType.EMAIL)
      private String email;
      //...
      
      }
      ```
    </details>
    
   * *TEXT* : String that conatains at least 7 words.
    
      <details>
      <summary><b>Code</b></summary>

      ```JAVA
      calss Product{

        @Criterion(data = DataType.TEXT)
        private String description;
        //...

        }
        ```
      </details>
     
* Advanced type : if none of the above predefined types fit your need, you can provide a regex using `regex` parameter.
    
    <details>
    <summary><b>Code</b></summary>
  
    ```JAVA
    calss Product{
    
      @Criterion(regex = "^([A-Za-z0-9]{5}-){4}[A-Za-z0-9]{5}$")
      private String code;
      //...
      
      }
    ```
  </details>
    
### Number Criteria : 
For fileds of type Number (Integer, Float, Double...), you can set the minimum and the maximum values using `minValue` and `maxValue` parameters.

<details>
<summary><b>Code</b></summary>

```JAVA
calss Product{

  @Criterion(minValue = "1", maxaValue="100")
  private Integer quantity;
  
  @Criterion(minValue = "10.5", maxValue="111.33")
  private Double price;
  //...

  }
  ```
</details>
    
    
### LocaDate Criteria: 
For Fields of type LocalDate you can set the minimum and the maximum dates using `minDate` and `maxDate` in this format `dd/mm/yyyy`.

<details>
<summary><b>Code</b></summary>

```JAVA
calss Product{

  @Criterion(minDate = "11/01/2022", maxDate="13/05/2022")
  private LocalDate shipDate;
  //...

  }
  ```
</details>

