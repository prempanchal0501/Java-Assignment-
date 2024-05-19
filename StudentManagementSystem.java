import java.util.ArrayList;
import java.util.Scanner;

// Student class
class Student {
    private int id;
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Student ko course enroll karne ke liye method
    public void enrollCourse(Course course) {
        courses.add(course);
        System.out.println(name + " has been enrolled in " + course.getCourseName());
    }

    // Student ki details print karne ka method
    public void printDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.print("Enrolled courses: ");
        for (Course course : courses) {
            System.out.print(course.getCourseName() + " ");
        }
        System.out.println();
    }

    public int getId() {
        return id;
    }
}

// Course class
class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    // Course ka naam return karne ka method
    public String getCourseName() {
        return courseName;
    }
}

// Teacher class
class Teacher {
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    public Teacher(String name) {
        this.name = name;
    }

    // Teacher ko course assign karne ka method
    public void assignCourse(Course course) {
        courses.add(course);
        System.out.println(name + " has been assigned to " + course.getCourseName());
    }

    // Teacher ki details print karne ka method
    public void printDetails() {
        System.out.println("Teacher Name: " + name);
        System.out.print("Assigned courses: ");
        for (Course course : courses) {
            System.out.print(course.getCourseName() + " ");
        }
        System.out.println();
    }

    // Teacher ka naam return karne ka method
    public String getName() {
        return name;
    }
}

// Main class
public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Enroll Student in Course");
            System.out.println("3. Assign Course to Teacher");
            System.out.println("4. Print Student Details");
            System.out.println("5. Print Teacher Details");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enrollStudentInCourse();
                    break;
                case 3:
                    assignCourseToTeacher();
                    break;
                case 4:
                    printStudentDetails();
                    break;
                case 5:
                    printTeacherDetails();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Naya student add karne ka method
    private static void addStudent() {
        System.out.println("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully.");
    }

    // Student ko course me enroll karne ka method
    private static void enrollStudentInCourse() {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter course name: ");
        String courseName = scanner.nextLine();
        Course course = findOrCreateCourse(courseName);
        student.enrollCourse(course);
    }

    // Teacher ko course assign karne ka method
    private static void assignCourseToTeacher() {
        System.out.println("Enter teacher name: ");
        String teacherName = scanner.nextLine();
        Teacher teacher = findOrCreateTeacher(teacherName);

        System.out.println("Enter course name: ");
        String courseName = scanner.nextLine();
        Course course = findOrCreateCourse(courseName);
        teacher.assignCourse(course);
    }

    // Student ki details print karne ka method
    private static void printStudentDetails() {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            student.printDetails();
        }
    }

    // Teacher ki details print karne ka method
    private static void printTeacherDetails() {
        System.out.println("Enter teacher name: ");
        String teacherName = scanner.nextLine();
        Teacher teacher = findTeacherByName(teacherName);
        if (teacher == null) {
            System.out.println("Teacher not found.");
        } else {
            teacher.printDetails();
        }
    }

    // Student ko ID se dhoondhne ka method
    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Course ko naam se dhoondhne ya naya course banane ka method
    private static Course findOrCreateCourse(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        Course newCourse = new Course(courseName);
        courses.add(newCourse);
        return newCourse;
    }

    // Teacher ko naam se dhoondhne ya naya teacher banane ka method
    private static Teacher findOrCreateTeacher(String name) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                return teacher;
            }
        }
        Teacher newTeacher = new Teacher(name);
        teachers.add(newTeacher);
        return newTeacher;
    }

    // Teacher ko naam se dhoondhne ka method
    private static Teacher findTeacherByName(String name) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                return teacher;
            }
        }
        return null;
    }
}

