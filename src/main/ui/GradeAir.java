package ui;

import model.Course;
import model.Weighting;
import model.Student;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class GradeAir {
    private static final String ADD_MARK = "add mark";
    private static final String SORT = "sort";
    private static final String ALPHABETICAL = "alphabetical";
    private static final String GRADE = "grade";
    private static final String COURSE_NAME = "course name";
    private static final String SUBJECT = "subject";
    private static final String TEACHER = "teacher";
    private static final String GRADE_TAKEN = "grade taken";
    private static final String WEIGHTING_SCHEME = "weighting scheme";
    private static final String ADD_COURSE = "add course";
    private static final String REMOVE_COURSE = "remove course";
    private static final String VIEW_COURSE = "view course";
    private static final String EDIT_ACCOUNT = "edit account";
    private static final String FIRST_NAME_FIELD = "first name";
    private static final String LAST_NAME_FIELD = "last name";
    private static final String VIEW_ACCOUNT = "view account";
    private static final String QUIT = "quit";

    private Scanner scanner;
    private Student student;
    private boolean runProgram;

    public static void main(String[] args) {
        GradeAir gradeAir = new GradeAir();
        System.out.println("Welcome to GradeAir!\n");

        gradeAir.createAccount();
        gradeAir.end();
    }

    //EFFECTS: launch GradeAir
    public GradeAir() {
        scanner = new Scanner(System.in);
        runProgram = true;
    }

    //EFFECTS: create a new account on GradeAir
    public void createAccount() {
        System.out.println("Let's get started! Type in your information below:");
        System.out.print("First Name: ");
        String fname = scanner.nextLine();

        System.out.print("Last Name: ");
        String lname = scanner.nextLine();

        student = new Student(fname, lname);
        System.out.println("Thanks for signing up for GradeAir!\n");

        homepage();
    }

    //EFFECTS: creates the homepage
    public void homepage() {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        System.out.println("Overall GPA: " + student.calculateGPA() + "%");
        displayCourses();
        printOptions();

        while (runProgram) {
            if (scanner.hasNext()) {
                String command = scanner.nextLine();
                command = makePrettyUserInput(command);
                parseInput(command);
            }
        }
    }

    public void initializeWeightings(Course course) {
        int totalWeightSoFar = 0;
        do {
            System.out.print("Category: ");
            String category = makePrettyUpperCase(scanner.nextLine());
            System.out.print("Weighting: ");
            int weighting = Integer.parseInt(scanner.nextLine());

            course.getWeightingScheme().add(new Weighting(category, weighting));
            totalWeightSoFar += course.getWeightingScheme().get(course.getWeightingScheme().size() - 1).getWeight();
        } while (!(totalWeightSoFar == 100));
    }

    //EFFECTS: Jump to whichever 'tab' user decides to go to
    @SuppressWarnings("methodlength")
    public void parseInput(String command) {
        switch (command) {
            case ADD_COURSE:
                parseAddCourse();
                break;
            case REMOVE_COURSE:
                System.out.println("What course would you like to remove?");
                String courseToRemove = makePrettyUpperCase(scanner.nextLine());
                student.getCourses().removeIf(obj -> obj.getCourseName().equals(courseToRemove));
                homepage();
            case SORT:
                sort();
                homepage();
            case VIEW_COURSE:
                viewCourses();
                homepage();
                break;
            case VIEW_ACCOUNT:
                viewUserFields();
                homepage();
                break;
            case QUIT:
                runProgram = false;
                break;
            default:
                System.out.println("Could not understand your input, please try again");
                break;
        }
    }

    public void parseAddCourse() {
        System.out.print("Enter the course name: ");
        String courseName = makePrettyUpperCase(scanner.nextLine());
        System.out.print("Enter the course subject: ");
        String subject = makePrettyUpperCase(scanner.nextLine());

        Course course = new Course(courseName, subject);
        System.out.println("Please assign a weighting scheme to the course (must add up to 100)");
        initializeWeightings(course);
        student.addCourse(course);
        homepage();
    }

    //EFFECTS: sorts courses according to what user wants
    public void sort() {
        System.out.println("How would you like to sort your courses?\n 'alphabetical' \n 'grade'");
        String command = makePrettyUserInput(scanner.nextLine());
        switch (command) {
            case ALPHABETICAL:
                student.sortCoursesAlphabetical();
            case GRADE:
                student.sortCoursesByGrade();
        }
    }

    //EFFECTS: Let user view a course out of their list of courses
    public void viewCourses() {
        System.out.println("Which course would you like to view?");
        String command = makePrettyUserInput(scanner.nextLine());

        for (Course course : student.getCourses()) {
            if (command.equalsIgnoreCase(course.getCourseName())) {
                showClassInfo(course);
            }
        }
    }

    //EFFECTS: Show course details for one course
    public void showClassInfo(Course course) {
        System.out.println(course.getCourseName() + ": " + course.getCourseGrade() + "%");
        System.out.println("Subject: " + course.getSubject());
        if (!isNull(course.getTeacher())) {
            System.out.println("Teacher: " + course.getTeacher());
        }
        if (!isNull(course.getGradeTaken())) {
            System.out.println("Grade Taken: " + course.getGradeTaken());
        }
        displayMarks(course);
        System.out.println("To add a mark, type " + ADD_MARK);
        System.out.println("To edit course info, type 'edit course', or press enter to go back home");
        String command = scanner.nextLine();
        if (command.equals("edit course")) {
            printCourseOptions();
            editCourseInfo(course);
        } else if (command.equals(ADD_MARK)) {
            parseAddMark(course);
        }
    }

    public void parseAddMark(Course course) {
        System.out.print("Name: ");
        String name = scanner.nextLine(); //TODO CAPITALIZE FIRST LETTER
        System.out.print("Mark from 0-100 (press enter if no mark yet): ");
        int mark = Integer.parseInt(scanner.nextLine());
        System.out.println("Type of mark (must be of a type that you initialized in your weighting scheme): ");
        String category = makePrettyUpperCase(scanner.nextLine());
        course.addMark(name, mark, category);
        showClassInfo(course);
    }

    //EFFECTS: Update course specific fields upon user input
    @SuppressWarnings("methodlength")
    public void editCourseInfo(Course course) {
        String command = makePrettyUserInput(scanner.nextLine());
        switch (command) {
            case COURSE_NAME:
                System.out.print("Enter course name: ");
                String opt1 = makePrettyUpperCase(scanner.nextLine());
                course.setCourseName(opt1);
                showClassInfo(course);
                break;
            case SUBJECT:
                System.out.print("Enter subject name: ");
                String opt2 = makePrettyUpperCase(scanner.nextLine());
                course.setSubject(opt2);
                showClassInfo(course);
                break;
            case TEACHER:
                System.out.print("Enter teacher name: ");
                String opt3 = makePrettyUpperCase(scanner.nextLine());
                course.setTeacher(opt3);
                showClassInfo(course);
                break;
            case GRADE_TAKEN:
                System.out.print("Enter grade taken: ");
                String opt4 = makePrettyUpperCase(scanner.nextLine());
                course.setGradeTaken(opt4);
                showClassInfo(course);
                break;
            default:
                System.out.println("Could not understand input, please try again");
                editCourseInfo(course);
                break;
        }
    }

    //EFFECTS: display mark for one course
    public void displayMarks(Course course) {
        System.out.println("Marks: ");
        for (Weighting weighting : course.getWeightingScheme()) {
            System.out.println(weighting.getCategory());
            if (weighting.getMarksList().size() == 0) {
                System.out.println("No " + weighting.getCategory() + " added yet");
            } else {
                System.out.println(weighting.retrieveMarksToPrint());
            }
        }
    }

    //EFFECTS: print every course
    public void displayCourses() {
        System.out.println("\nClasses:");

        if (student.getCourses().size() == 0) {
            System.out.println("You have no classes added");
        } else {
            for (Course course : student.getCourses()) {
                System.out.println(course.getCourseName() + ": " + course.getCourseGrade() + "%");
            }
        }
    }

    //EFFECTS: Update user account fields
    public void updateUserFields() {
        System.out.println("What field would you like to update?\n 'first name' \n 'last name'");
        String command = makePrettyUserInput(scanner.nextLine());
        switch (command) {
            case FIRST_NAME_FIELD:
                System.out.print("Enter first name: ");
                String opt1 = scanner.nextLine();
                student.setFirstName(opt1);
                printOptions();
                break;
            case LAST_NAME_FIELD:
                System.out.print("Enter last name: ");
                String opt2 = scanner.nextLine();
                student.setLastName(opt2);
                printOptions();
                break;
            default:
                System.out.println("Could not understand your input, please try again");
                updateUserFields();
                break;
        }
    }

    //EFFECTS: show user account information and present option to edit account information
    public void viewUserFields() {
        System.out.println("\nHere is your account information");
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());

        System.out.println("To edit this information, type '" + EDIT_ACCOUNT + "', or press enter to go back");
        if (scanner.nextLine().equals(EDIT_ACCOUNT)) {
            updateUserFields();
        }
    }

    //EFFECTS: show available user options for 'switching' tabs on homepage
    public void printOptions() {
        if (student.getCourses().size() != 0) {
            System.out.println("\nTo view any course more specifically, type '" + VIEW_COURSE + "'");
            System.out.println("To add a course, type '" + ADD_COURSE + "'");
            System.out.println("To remove a course, type '" + REMOVE_COURSE + "'");
            System.out.println("To sort courses, type '" + SORT + "'");
        } else {
            System.out.println("\nTo add a course, type '" + ADD_COURSE + "'");
        }
        System.out.println("To view your account, type '" + VIEW_ACCOUNT + "'");
        System.out.println(("To quit the application, type '" + QUIT + "'"));
    }

    //EFFECTS: show available user inputs to edit a class
    public void printCourseOptions() {
        System.out.println("To change course name, type '" + COURSE_NAME + "'");
        System.out.println("To change the subject, type '" + SUBJECT + "'");
        System.out.println("To change teacher name, type '" + TEACHER + "'");
        System.out.println("To change the grade taken, type '" + GRADE_TAKEN + "'");
    }

    //EFFECTS: makes user input lowercase and no white space
    public String makePrettyUserInput(String str) {
        str = str.toLowerCase();
        str = str.trim();
        return str;
    }

    //EFFECTS: make user input all upper case and trimmed
    public String makePrettyUpperCase(String str) {
        str = str.toUpperCase();
        str = str.trim();
        return str;
    }

    //EFFECTS: end the program
    public void end() {
        System.out.println("Quitting...");
        scanner.close();
    }
}
