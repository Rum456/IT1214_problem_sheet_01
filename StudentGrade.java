import java.text.DecimalFormat;

// 1. Create a class named Student
class Student {
    private String name;
    private int exam1;
    private int exam2;
    private int exam3;

    // 2. Create a constructor
    public Student(String name, int exam1, int exam2, int exam3) {
        this.name = name;
        // 3. Implement input validation in the constructor
        setExam1(exam1);
        setExam2(exam2);
        setExam3(exam3);
    }

    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Exam scores must be between 0 and 100.");
        }
    }

    // 4. Create getter methods for each field
    public String getName() {
        return name;
    }

    public int getExam1() {
        return exam1;
    }

    public int getExam2() {
        return exam2;
    }

    public int getExam3() {
        return exam3;
    }

    // Setter methods with validation
    public void setExam1(int exam1) {
        validateScore(exam1);
        this.exam1 = exam1;
    }

    public void setExam2(int exam2) {
        validateScore(exam2);
        this.exam2 = exam2;
    }

    public void setExam3(int exam3) {
        validateScore(exam3);
        this.exam3 = exam3;
    }

    // 5. Create a method named calculateAverage()
    public double calculateAverage() {
        return (double) (exam1 + exam2 + exam3) / 3.0;
    }
}

 class StudentGradeCalculator {
    public static void main(String[] args) {
        Student student1 = null; // Initialize to null
        Student student2 = null;

        System.out.println("Attempting to create Student 'John' with invalid scores:");
        try {
            student1 = new Student("John", 75, 110, 90);
            System.out.println("Student 'John' created successfully.");
        } catch (IllegalArgumentException e) {
            // o If an exception is caught, print the exception message.
            System.out.println("Error creating student 'John': " + e.getMessage());
        }

        System.out.println("\nAttempting to create Student 'Jane' with valid scores:");
        // Create another student with valid scores to show successful creation
        try {
            student2 = new Student("Jane", 85, 92, 78);
            System.out.println("Student 'Jane' created successfully.");
            // o If creation succeeds, print the student’s name and average exam score
            // (formatted to two decimal places).
            if (student2 != null) {
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println("Student Name: " + student2.getName());
                System.out.println("Average Score: " + df.format(student2.calculateAverage()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating student 'Jane': " + e.getMessage());
        }

        //  check student1 here, it will be null if an exception occurred during its creation
        if (student1 != null) {
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("\nStudent Name (John): " + student1.getName());
            System.out.println("Average Score (John): " + df.format(student1.calculateAverage()));
        }
    }
}