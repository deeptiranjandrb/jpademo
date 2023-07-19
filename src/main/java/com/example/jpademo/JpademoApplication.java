package com.example.jpademo;

import com.example.jpademo.dao.StudentDAO;
import com.example.jpademo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JpademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpademoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            Scanner in = new Scanner(System.in);
            System.out.println("Please choose from below options:");
            options();
            boolean flag = true;
            while(flag) {
                String option = in.next();
                switch (option) {
                    case "1" -> createStudent(studentDAO);
                    case "2" -> createMultipleStudents(studentDAO);
                    case "3" -> readStudent(studentDAO);
                    case "4" -> queryForStudents(studentDAO);
                    case "5" -> queryForStudentsByFirstName(studentDAO);
                    case "6" -> updateStudent(studentDAO);
                    case "7" -> deleteStudent(studentDAO);
                    case "8" -> deleteAllStudents(studentDAO);
                    case "9" -> flag = false;
                    default -> {
                        System.out.println("please select a correct option:");
                        options();
                    }
                }
            }
        };
    }
    private void options(){
        System.out.println("1.Create a Student");
        System.out.println(("2.Create multiple Students"));
        System.out.println("3.Read a single Student");
        System.out.println("4.Read all Students");
        System.out.println("5.Read by first name");
        System.out.println("6.Update Student");
        System.out.println("7.Delete a Student");
        System.out.println("8.Delete all Students");
        System.out.println("9.Exit");

    }
    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students");
        System.out.println("num of rows deleted: " + studentDAO.deleteAll());
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        // retrieve student based on id
        int studentId = 1;
        System.out.println("Getting the student with id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);
        // change first name to "scooby"
        System.out.println("updating student ...");
        myStudent.setFirstName("Scooby");


        // update the student
        studentDAO.update(myStudent);

        // display the updated student
        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByFirstName(StudentDAO studentDAO) {
        // get a list of students
        List<Student> theStudents = studentDAO.findByFirstname("paul");
        // display list of students
        for(Student tempStudent : theStudents)
         System.out.println(tempStudent);
    }

    private void queryForStudents(StudentDAO studentDAO) {
        // get a list of students
        List<Student> theStudents = studentDAO.findAll();
        // display list of students
        for (Student tempStudent: theStudents){
            System.out.println(tempStudent);
        }
    }

    private void createStudent(StudentDAO studentDAO){
        // create the student object
        System.out.println("Creating  student object ...");
        Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

        // save the student object
        System.out.println("Saving the student ...");
        studentDAO.save(tempStudent);
        // display the id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());

    }

    private void createMultipleStudents(StudentDAO studentDAO){
        System.out.println("Creating 3 student object ...");
        Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");
        Student tempStudent2 = new Student("Paul2", "Doe", "paul@gmail.com");
        Student tempStudent3 = new Student("Paul3", "Doe", "paul@gmail.com");

        // save the student objects
        System.out.println("saving the students...");
        studentDAO.save(tempStudent);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }


    private void readStudent(StudentDAO studentDAO) {

        //create a student
        System.out.println("Creating  student object ...");
        Student tempStudent = new Student("paulina", "Doe", "paulina@gmail.com");
        // save the student
        System.out.println("Saving the student ...");
        studentDAO.save(tempStudent);
        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Id of the saved student: "+ theId);

        // retrieve student based on the id: primary key
        Student savedStudent = studentDAO.findById(theId);

        // display student
        System.out.println("Found the student: " + savedStudent);


    }
    }
