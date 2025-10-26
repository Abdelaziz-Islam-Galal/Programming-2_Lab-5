
public class Validation {
    public static boolean isValidString(String str) {
        return (str != null && !str.trim().isEmpty());
    }

    public static boolean isValidInt(int studentId) {
        return studentId > 0;
    }

    public static boolean isValidAge(int age) {
        return age > 15 && age < 40;
    }

    public static boolean isValidGPA(float gpa) {
        return gpa >= 0.0f && gpa <= 4.0f;
    }


}
