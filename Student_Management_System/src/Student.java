

public class Student  {
    private int StudentId;
    private String Fname;
    private int Age;
    private String Gender;
    private String Department;
    private float GPA;

    public Student(int StudentId, String Fname, int Age, String Gender, String Department, float GPA) {
            
        if (!Validation.isValidInt(StudentId)) {
            throw new IllegalArgumentException("Invalid Student ID");
        }
        if (!Validation.isValidString(Fname)) {
            throw new IllegalArgumentException("Invalid Full Name");
        }
        if (!Validation.isValidAge(Age)) {
            throw new IllegalArgumentException("Invalid Age");
        }
        if (!Validation.isValidGender(Gender)) {
            throw new IllegalArgumentException("Invalid Gender");
        }
        if (!Validation.isValidString(Department)) {
            throw new IllegalArgumentException("Invalid Department");
        }
        if (!Validation.isValidGPA(GPA)) {
            throw new IllegalArgumentException("Invalid GPA");
        }
        this.StudentId = StudentId;
        this.Fname = Fname;
        this.Age = Age;
        this.Gender = Gender;
        this.Department = Department;
        this.GPA = GPA;
    }

        
    // Setters with validation
    public void setStudentId(int studentId) {
        if (!Validation.isValidInt(studentId)) {
            throw new IllegalArgumentException("Invalid student ID");
        }
        this.StudentId = studentId;
    }

    public void setFname(String Fname) {
        if (!Validation.isValidString(Fname)) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.Fname = Fname;
    }

    public void setGender(String Gender) {
        if (!Validation.isValidGender(Gender)) {
            throw new IllegalArgumentException("Invalid gender");
        }
        this.Gender = Gender;
    }

    public void setDepartment(String Department) {
        if (!Validation.isValidString(Department)) {
            throw new IllegalArgumentException("Invalid department");
        }
        this.Department = Department;
    }

    public void setGPA(float GPA) {
        if (!Validation.isValidGPA(GPA)) {
            throw new IllegalArgumentException("Invalid GPA");
        }
        this.GPA = GPA;
    }

    public void setAge(int Age) {
        if (!Validation.isValidAge(Age)) {
            throw new IllegalArgumentException("Invalid Age");
        }
        this.Age = Age;
    }

    // Getters
    public int getStudentId() {
        return StudentId;
    }

    public String getFname() {
        return Fname;
    }

    public String getGender() {
        return Gender;
    }

    public String getDepartment() {
        return Department;
    }

    public float getGPA() {
        return GPA;
    }

    public int getAge() {
        return Age;
    }

}
