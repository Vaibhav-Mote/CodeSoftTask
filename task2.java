package codeSoftTask;
import java.util.*;

class GradeCalculator {
    public String calculateGrade(int averagePercentage) {
        switch (averagePercentage / 10) {
            case 10:
                return "A+";
            case 9:
                return "A";
            case 8:
                return "B+";
            case 7:
                return "B";
            case 6:
                return "C";
            case 5:
                return "D";
            default:
                return "F";
        }
    }
}

public class task2 {
    public static void main(String[] args) {
        GradeCalculator gradeCalculator = new GradeCalculator();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects:");
        int numberOfSubjects = scanner.nextInt();
        int totalMarks = 0;

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.println("Enter the marks obtained in subject " + (i + 1) + ":");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }

        int averagePercentage = totalMarks / numberOfSubjects;
        String grade = gradeCalculator.calculateGrade(averagePercentage);

        System.out.println("Total marks obtained: " + totalMarks);
        System.out.println("Average percentage obtained: " + averagePercentage + "%");
        System.out.println("Grade obtained: " + grade);

        scanner.close();
    }
}
