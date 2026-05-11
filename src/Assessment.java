package gradetracker;

public class Assessment {
    private String name;
    private double score;
    private double maxScore;
    private double weight; // Percentage (e.g., 0.20 for 20%)

    public Assessment(String name, double score, double maxScore, double weight) {
        this.name = name;
        this.score = score;
        this.maxScore = maxScore;
        this.weight = weight;
    }

    public double getWeightedPercentage() {
        return (score / maxScore) * weight;
    }

    public String getName() { return name; }
    public double getScore() { return score; }
    public double getMaxScore() { return maxScore; }
    public double getWeight() { return weight; }
}
