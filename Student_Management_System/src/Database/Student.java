
package Database;

public class Student implements ObjectInfo {
    private int studentID;
    private String name;
    private String department;
    private int age;
    private float gpa;
    private String gender;

    
    public Student(int studentID, String name, int age,String gender,String department, float gpa){
          
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;
    } 


    @Override
    public String lineRepresentation() {
        return studentID + "," + name + "," + age + "," + gender + "," + department + "," + gpa;
    }

    @Override
    public int getSearchKey() {
       return studentID;
    }
}
