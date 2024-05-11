package com.yesser.app;

import com.yesser.app.entity.Student;
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
        Transaction transaction = null;
        List<Student> students = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from student",
                    Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void saveStudent(Student student) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
