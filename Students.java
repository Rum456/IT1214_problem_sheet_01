import java.util.*;


class Student {
    private int studentId;
    private String name;
    private int daysAttended;

    public Student(int studentId, String name, int daysAttended) {
        this.studentId = studentId;
        this.name = name;
        this.daysAttended = daysAttended;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysAttended() {
        return daysAttended;
    }

    public void setDaysAttended(int daysAttended) {
        this.daysAttended = daysAttended;
    }

    
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Days Attended: " + daysAttended;
    }
}


class Classroom {
    private Student[] students;
    private int numberOfStudents;
    private static final int MAX_CAPACITY = 10;

    public Classroom() {
        this.students = new Student[MAX_CAPACITY];
        this.numberOfStudents = 0;
    }

    public void addStudent(Student student) {
        if (numberOfStudents < MAX_CAPACITY) {
            students[numberOfStudents] = student;
            numberOfStudents++;
            System.out.println("Student " + student.getName() + " added successfully.");
        } else {
            System.out.println("Classroom is full. Cannot add more students.");
        }
    }

    public void updateDaysAttended(int studentId, int newDaysAttended) {
        boolean found = false;
        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].getStudentId() == studentId) {
                students[i].setDaysAttended(newDaysAttended);
                System.out.println("Attendance for student " + students[i].getName() + " (ID: " + studentId + ") updated to " + newDaysAttended + " days.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }

    public void displayAllStudents() {
        if (numberOfStudents == 0) {
            System.out.println("No students in the classroom.");
            return;
        }
        System.out.println("\n--- Student Details ---");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println(students[i].toString());
        }
        System.out.println("-----------------------");
    }
}



 class Students_main {
    public static void main(String[] args) {
        // Create an instance of Classroom
        Classroom myClassroom = new Classroom();

        // Add the three students from the table
        myClassroom.addStudent(new Student(101, "Alice Smith", 12));
        myClassroom.addStudent(new Student(102, "Bob Jones", 15));
        myClassroom.addStudent(new Student(103, "Carol Lee", 10));

        // Update Bob Jones’s attendance to 16 days
        myClassroom.updateDaysAttended(102, 16);

        // Attempt to update attendance for a student with ID 104 (who does not exist)
        myClassroom.updateDaysAttended(104, 8);

        // Display all student details
        myClassroom.displayAllStudents();
    }
}
