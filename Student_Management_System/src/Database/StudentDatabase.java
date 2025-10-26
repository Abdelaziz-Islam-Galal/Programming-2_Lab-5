
package Database;

public class StudentDatabase extends DatabaseTemplate <Student> {

    public StudentDatabase(String filename) {
        super(filename);
    }

    @Override
    public Student createRecordFrom(String line) {
       
    String[] fields = line.split(",");
    return new Student(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), 
    fields[3], fields[4], Float.parseFloat(fields[5]));

    }

}
