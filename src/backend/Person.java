package backend;
import java.util.Scanner;
public class Person {

    private String name, uName, pass;
    Scanner acc = new Scanner(System.in);

    public Person(){
        this.uName = "No user name";
        this.name = "No name";
        this.pass = "No password";
    }

    public Person(String name, String uName, String pass){
        if(name == null || uName == null || pass == null){
            System.out.println("Error::");
        }else{
            this.uName = uName;
            this.name = name;
            this.pass = pass;
        }
    }

    public Person(Person person){
        if(person.name == null || person.uName == null || person.pass == null){
            System.out.println("Error::");
        }else{
            this.uName = person.uName;
            this.name = person.name;
            this.pass = person.pass;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUName(String uName){
        this.uName = uName;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getName(){
        return name;
    }

    public String getUName(){
        return uName;
    }

    public String getPass(){
        return pass;
    }
}