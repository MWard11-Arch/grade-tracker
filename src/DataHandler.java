package gradetracker;

import java.io.*;
import java.util.*;

public class DataHandler {
    private static final String FILE_PATH = "grades.csv";

    public static void save(List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Course course : courses) {
                // Course line: C,Name,Credits
                writer.println("C," + course.getName() + "," + course.getCredits());
                for (Assessment a : course.getAssessments()) {
                    // Assessment line: A,Name,Score,Max,Weight
                    writer.println("A," + a.getName() + "," + a.getScore() + "," + a.getMaxScore() + "," + a.getWeight());
                }
            }
            System.out.println("Data saved to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<Course> load() {
        List<Course> courses = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return courses;

        try (Scanner scanner = new Scanner(file)) {
            Course currentCourse = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals("C")) {
                    currentCourse = new Course(parts[1], Integer.parseInt(parts[2]));
                    courses.add(currentCourse);
                } else if (parts[0].equals("A") && currentCourse != null) {
                    currentCourse.addAssessment(new Assessment(
                        parts[1], 
                        Double.parseDouble(parts[2]), 
                        Double.parseDouble(parts[3]), 
                        Double.parseDouble(parts[4])
                    ));
                }
            }
            System.out.println("Data loaded from " + FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("No save file found.");
        }
        return courses;
    }
}
