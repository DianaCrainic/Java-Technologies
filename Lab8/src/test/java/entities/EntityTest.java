package entities;

import com.uaic.lab7.entities.Admin;
import com.uaic.lab7.entities.Author;
import com.uaic.lab7.entities.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EntityTest extends TestJPA {

    @Test
    public void test1_createAuthor() {
        entityManager.getTransaction().begin();
        Author author = new Author("author222", "author222");
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test2_getNumberOfAuthors() {
        entityManager.getTransaction().begin();
        List authors = entityManager.createQuery("SELECT author FROM Author author").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(authors.size(), 3);
    }

    @Test
    public void test3_createAdmin() {
        entityManager.getTransaction().begin();
        Admin admin = new Admin("admin1212", "admin1212");
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test4_getNumberOfAdmins() {
        entityManager.getTransaction().begin();
        List admins = entityManager.createQuery("SELECT admin FROM Admin admin").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(admins.size(), 2);
    }

    @Test
    public void test5_createDocument() {
        entityManager.getTransaction().begin();
        String stringContent = "Content of document";
        byte[] documentContent = stringContent.getBytes();
        Author newAuthor = new Author("author333", "author333");
        entityManager.persist(newAuthor);
        Document document = new Document("Document5", 112233, documentContent);
        document.setAuthor(newAuthor);
        entityManager.persist(document);
        entityManager.getTransaction().commit();
    }

    @Test
    public void test6_getNumberOfDocuments() {
        entityManager.getTransaction().begin();
        List documents = entityManager.createQuery("SELECT document FROM Document document").getResultList();
        entityManager.getTransaction().commit();
        Assert.assertEquals(documents.size(), 4);
    }
}