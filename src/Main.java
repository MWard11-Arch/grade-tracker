package gradetracker;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Course> courses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        courses = DataHandler.load();
        System.out.println("--- Arch Academic Command Center: Grade Tracker ---");
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Course");
            System.out.println("2. Add Assessment to Course");
            System.out.println("3. View Grades");
            System.out.println("4. Save & Exit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    addAssessment();
                    break;
                case "3":
                    viewGrades();
                    break;
                case "4":
                    DataHandler.save(courses);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Credits: ");
        int credits = Integer.parseInt(scanner.nextLine());
        courses.add(new Course(name, credits));
        System.out.println("Course added.");
    }

    private static void addAssessment() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }
        System.out.print("Select course: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Assessment Name: ");
        String name = scanner.nextLine();
        System.out.print("Score: ");
        double score = Double.parseDouble(scanner.nextLine());
        System.out.print("Max Score: ");
        double max = Double.parseDouble(scanner.nextLine());
        System.out.print("Weight (e.g., 0.20): ");
        double weight = Double.parseDouble(scanner.nextLine());

        courses.get(index).addAssessment(new Assessment(name, score, max, weight));
        System.out.println("Assessment added.");
    }

    private static void viewGrades() {
        if (courses.isEmpty()) {
            System.out.println("No courses to display.");
            return;
        }
        for (Course c : courses) {
            System.out.printf("%s (%d credits): %.2f%%\n", c.getName(), c.getCredits(), c.calculateGrade());
            for (Assessment a : c.getAssessments()) {
                System.out.printf("  - %s: %.2f/%.2f (w: %.2f)\n", a.getName(), a.getScore(), a.getMaxScore(), a.getWeight());
            }
        }
    }
}
