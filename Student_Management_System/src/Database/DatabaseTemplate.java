
package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public abstract class DatabaseTemplate <T extends ObjectInfo> {

  private ArrayList<T> records = new ArrayList<>();
  private String filename;

  public DatabaseTemplate(String filename) { 
    
      this.filename = filename;
  }

  public abstract T createRecordFrom(String line); 

  public void readFromFile() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(this.filename));
    String line;

    while ((line = br.readLine()) != null) { 
      T record = createRecordFrom(line);
      records.add(record);
    }
    br.close();
  }

  public ArrayList<T> returnAllRecords() {
    return records; 
  }

  public boolean contains(String key) {

    for (int i = 0; i < records.size(); i++) { 
      if (key != null && key.equals(records.get(i).getSearchKey()))
        return true;
    }
    return false;
  }

  public T getRecord(String key) {

    for (int i = 0; i < records.size(); i++) { 
      if (key != null && key.equals(records.get(i).getSearchKey()))
        return records.get(i);
    }
    System.out.println("RECORD (TO BE RETURNED) NOT FOUND!");
    return null;
  }

  public void insertRecord(T record) {

    for (int i = 0; i < records.size(); i++) { 
      if (record != null && record.equals(records.get(i))) {
        System.out.println("RECORD (TO BE INSERTED) ALREADY EXISTS!");
        return;
      }
    }
    records.add(record);
  }

  public void deleteRecord(String key) {

    for (int i = 0; i < records.size(); i++) { 
      if (key != null && key.equals(records.get(i).getSearchKey())) {
        records.remove(i);
        return;
      }
    }
    System.out.println("RECORD (TO BE DELETED) NOT FOUND!");
  }

  public void saveToFile() throws IOException {

    FileWriter fw = new FileWriter(this.filename, false);

    for (int i = 0; i < records.size(); i++)
      fw.write(records.get(i).lineRepresentation() + "\n");

    fw.close();
  }

  public int numberOfRecords() {
    return records.size();
  }
}