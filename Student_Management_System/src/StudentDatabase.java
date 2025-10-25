import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private final String filename = "data.txt";
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
        Student unupdatedStudent = searchStudentById(updatedStudent.getStudentId());
        if (unupdatedStudent != null) {
            unupdatedStudent.setFname(updatedStudent.getFname());
            unupdatedStudent.setDepartment(updatedStudent.getDepartment());
            unupdatedStudent.setAge(updatedStudent.getAge());
            unupdatedStudent.setGPA(updatedStudent.getGPA());
            unupdatedStudent.setGender(updatedStudent.getGender());
            saveToFile();
        } else {
            List<Student> result = searchStudentByName(updatedStudent.getFname());
            if (result.size() == 1) {
                unupdatedStudent = result.get(0);
                unupdatedStudent.setStudentId(updatedStudent.getStudentId());
                unupdatedStudent.setDepartment(updatedStudent.getDepartment());
                unupdatedStudent.setAge(updatedStudent.getAge());
                unupdatedStudent.setGPA(updatedStudent.getGPA());
                unupdatedStudent.setGender(updatedStudent.getGender());
                saveToFile();
            } else {
                if (result.size() > 1) {
                    throw new IllegalArgumentException(
                            "Multiple students found with the same name; use Student ID to update.");
                } else {
                    throw new IllegalArgumentException("Student not found.");
                }
            }
        }

    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(this.filename, false);
            for (int i = 0; i < students.size(); i++) {
                fw.write(lineRepresentation(students.get(i)) + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save data: " + e.getMessage());
            return;
        }
    }

    public void loadFromFile() {
        // to be implemented

    }

    private String lineRepresentation(Student student) {
        return student.getStudentId() + "," + student.getFname() + "," + student.getGender() + "," +
                student.getDepartment() + "," + student.getGPA() + "," + student.getAge();
    }
}