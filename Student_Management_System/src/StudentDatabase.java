import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private List<Student> students;

    public StudentDatabase(List<Student> students) {
        this.students = students;
    }

    
    public boolean addStudent(Student student) {
       
        for (Student s : students) {
            if (s.getStudentId() == student.getStudentId()) {
                return false; 
            }
        }
        students.add(student);
        saveToFile();
        return true;
    }
    
        public boolean deleteStudent(int studentId) {
        boolean removed = students.removeIf(s -> s.getStudentId() == studentId);
        
        if (removed) {
            saveToFile();
        }
        
        return removed;
    }

   
    public Student searchStudentById(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

    
    public List<Student> searchStudentByName(String fname) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getFname().equalsIgnoreCase(fname)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void UpdateStudent(Student updatedStudent) {
      // to be implemented

    }

    public void saveToFile() {
       // to be implemented
    }

    public void loadFromFile() {
        // to be implemented
        
    }
}