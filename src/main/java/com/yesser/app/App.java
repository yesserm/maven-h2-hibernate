package com.yesser.app;

import com.yesser.app.entity.Product;
import com.yesser.app.entity.Software;
import com.yesser.app.entity.Student;
import com.yesser.app.entity.Supplier;
import com.yesser.app.services.GenericServiceImpl;
import com.yesser.app.services.IGenericService;
import com.yesser.app.util.HibernateUtil;
import com.yesser.app.views.MiVentana;
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

        IGenericService<Supplier> supplierService = new GenericServiceImpl<>(Supplier.class,
                HibernateUtil.getSessionFactory());
        Supplier supplier = new Supplier("URACCAN INC");
        supplier.getProducts().add(
                new Product(supplier, "Fruta de pan", "comida", 10.00));
        supplier.getProducts().add(
                new Product(supplier, "Silla metalica convertible", "Mueble", 20.0)
        );
        supplierService.save(supplier);

        Supplier supplier1 = new Supplier("Centro de innovacion");
        supplier1.getProducts().add(
                new Software(supplier1, "Adobe Photoshop CC 2021", "Editor de fotos", 300.0, "C++")
        );
        supplier1.getProducts().add(
                new Software(supplier1, "Intellij Idea Ultimate 2023", "Entorno de desarrollo", 500.0, "Java")
        );
        supplierService.save(supplier1);


        new MiVentana();

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
