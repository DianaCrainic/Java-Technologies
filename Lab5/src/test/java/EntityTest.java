import com.uaic.lab3.entities.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EntityTest extends testJPA{

    @Test
    public void test1_createStudent(){
        entityManager.getTransaction().begin();
        Student student = new Student("Isabela Crainic", "1,3");
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test2_getNumberOfStudents() {
        entityManager.getTransaction().begin();
        List students = entityManager.createQuery("SELECT student FROM Student student").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(students.size(), 3);
    }
}
