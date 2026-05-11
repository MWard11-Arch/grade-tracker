package gradetracker;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int credits;
    private List<Assessment> assessments;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
        this.assessments = new ArrayList<>();
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    public double calculateGrade() {
        double totalWeight = 0;
        double currentTotal = 0;
        for (Assessment a : assessments) {
            currentTotal += a.getWeightedPercentage();
            totalWeight += a.getWeight();
        }
        if (totalWeight > 0) {
            return (currentTotal / totalWeight) * 100;
        } else {
            return 0;
        }
    }

    public String getName() { return name; }
    public int getCredits() { return credits; }
    public List<Assessment> getAssessments() { return assessments; }
}
