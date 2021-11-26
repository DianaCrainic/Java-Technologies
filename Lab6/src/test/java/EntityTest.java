import com.uaic.lab3.entities.ProjectPresentation;
import com.uaic.lab3.entities.Resource;
import com.uaic.lab3.entities.Student;
import com.uaic.lab3.entities.WrittenTest;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

public class EntityTest extends testJPA {

    @Test
    public void test1_createStudent() {
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

    @Test
    public void test3_createWrittenTest() {
        entityManager.getTransaction().begin();
        WrittenTest exam = new WrittenTest("Crypto", new Timestamp(System.currentTimeMillis()), 45, "Courses");
        entityManager.persist(exam);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test4_createProjectPresentation() {
        entityManager.getTransaction().begin();
        ProjectPresentation exam = new ProjectPresentation("Data Structures", new Timestamp(System.currentTimeMillis()), 120, true);
        entityManager.persist(exam);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test5_selectAllWittenTests() {
        entityManager.getTransaction().begin();
        List exams = entityManager.createQuery("SELECT exam FROM WrittenTest exam").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(exams.size(), 4);
    }

    @Test
    public void test6_selectAllProjectPresentations() {
        entityManager.getTransaction().begin();
        List exams = entityManager.createQuery("SELECT exam FROM ProjectPresentation exam").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(exams.size(), 2);
    }

    @Test
    public void test7_createResource() {
        entityManager.getTransaction().begin();
        Resource resource = new Resource("speakers", 7);
        entityManager.persist(resource);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test8_selectAllResources() {
        entityManager.getTransaction().begin();
        List resources = entityManager.createQuery("SELECT resource FROM Resource resource").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(resources.size(), 3);
    }

}
