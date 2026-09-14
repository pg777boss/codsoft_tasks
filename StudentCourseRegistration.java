import java.util.ArrayList;
import java.util.Scanner;
class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;
    Course(String code, String title, String description,
           int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }
    boolean hasAvailableSlot() {
        return registeredStudents < capacity;
    }
}
class Student {
    int studentId;
    String name;
    ArrayList<Course> registeredCourses;
    Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        registeredCourses = new ArrayList<>();
    }
}
public class StudentCourseRegistration {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        addCourses();
        addStudents();
        System.out.println("======================================");
        System.out.println("   STUDENT COURSE REGISTRATION SYSTEM");
        System.out.println("======================================");
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    displayStudentCourses();
                    break;
                case 5:
                    System.out.println("\nThank you for using the system!");
                    break;
                default:
                    System.out.println("\nInvalid choice!");
            }
        } while (choice != 5);
        scanner.close();
    }
    // Add courses to the database
    static void addCourses() {
        courses.add(new Course(
                "CS101",
                "Java Programming",
                "Learn Java programming basics",
                3,
                "Monday 10:00 AM"
        ));
        courses.add(new Course(
                "CS102",
                "Database Management",
                "Learn database concepts",
                3,
                "Tuesday 11:00 AM"
        ));
        courses.add(new Course(
                "CS103",
                "Web Development",
                "Learn HTML, CSS and JavaScript",
                2,
                "Wednesday 2:00 PM"
        ));
    }
    // Add students to the database
    static void addStudents() {
        students.add(new Student(101, "Rahul"));
        students.add(new Student(102, "Amit"));
        students.add(new Student(103, "Priya"));
    }
    // Display menu
    static void displayMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for Course");
        System.out.println("3. Remove Course");
        System.out.println("4. View My Courses");
        System.out.println("5. Exit");
        System.out.println("----------------------------");
    }
    // Display available courses
    static void displayCourses() {
        System.out.println("\n========== AVAILABLE COURSES ==========");
        for (Course course : courses) {
            int availableSlots =
                    course.capacity - course.registeredStudents;
            System.out.println("\nCourse Code : " + course.code);
            System.out.println("Title       : " + course.title);
            System.out.println("Description : " + course.description);
            System.out.println("Schedule    : " + course.schedule);
            System.out.println("Available Slots : " + availableSlots);
        }
    }
    // Find student
    static Student findStudent(int id) {
        for (Student student : students) {
            if (student.studentId == id) {
                return student;
            }
        }
        return null;
    }
    // Find course
    static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
    // Register student for course
    static void registerCourse() {
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        displayCourses();
        System.out.print("\nEnter Course Code: ");
        String code = scanner.nextLine();
        Course course = findCourse(code);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        if (!course.hasAvailableSlot()) {
            System.out.println("Sorry! No available slots.");
            return;
        }
        if (student.registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
            return;
        }
        student.registeredCourses.add(course);
        course.registeredStudents++;
        System.out.println("Course registered successfully!");
    }
    // Remove registered course
    static void removeCourse() {
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.print("Enter Course Code to remove: ");
        String code = scanner.nextLine();
        Course course = findCourse(code);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        if (student.registeredCourses.remove(course)) {
            course.registeredStudents--;
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
    // Display student's registered courses
    static void displayStudentCourses() {
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println("\nCourses registered by " + student.name + ":");
        if (student.registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : student.registeredCourses) {
                System.out.println(course.code + " - " + course.title);
            }
        }
    }
}