package com.yesser.app;

import com.yesser.app.entity.Student;
import com.yesser.app.services.GenericServiceImpl;
import com.yesser.app.services.IGenericService;
import com.yesser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Student student = new Student("Yesser", "Miranda", "yesser@mail.com");
        saveStudent(student);
        List<Student> students = getStudents();
        students.forEach(System.out::println);
    }

    private static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        IGenericService<Student> studentSevice = new GenericServiceImpl<>(Student.class, HibernateUtil.getSessionFactory());
        students = studentSevice.getAll();
        return students;
    }

    private static void saveStudent(Student student) {
        IGenericService<Student> studentService = new GenericServiceImpl<>(Student.class, HibernateUtil.getSessionFactory());
        studentService.save(student);
    }
}
