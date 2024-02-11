
package codeSoftTask;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void removeStudent() {
        System.out.print("Enter student roll number to remove: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void searchStudent() {
        System.out.print("Enter student roll number to search: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found:\n" + student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveDataToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            outputStream.writeObject(students);
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void readDataFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (ArrayList<Student>) inputStream.readObject();
            System.out.println("Data loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.readDataFromFile();

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data to File");
            System.out.println("6. Exit");

            System.out.print("Select an option (1-6): ");
            int choice = Integer.parseInt(sms.scanner.nextLine());

            switch (choice) {
                case 1:
                    sms.addStudent();
                    break;
                case 2:
                    sms.removeStudent();
                    break;
                case 3:
                    sms.searchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.saveDataToFile();
                    break;
                case 6:
                    sms.saveDataToFile(); // Save data before exiting
                    System.out.println("Exiting Student Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
