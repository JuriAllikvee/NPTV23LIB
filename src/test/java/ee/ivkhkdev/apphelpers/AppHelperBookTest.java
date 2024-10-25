package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppHelperBookTest {
    Input inputMock;
    Service<Author> authorServiceMock;
    PrintStream outDefault;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        authorServiceMock = Mockito.mock(Service.class);
        outDefault = System.out;
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        authorServiceMock = null;
        System.setOut(outDefault);
        outMock = null;
    }

    @Test
    void create() {
        when(inputMock.getString()).thenReturn("Book Title", "n", "1", "1", "2023");
        when(authorServiceMock.list()).thenReturn(List.of(new Author("Lev", "Tolstoy")));

        AppHelperBook appHelperBook = new AppHelperBook(inputMock, authorServiceMock);
        Book actual = appHelperBook.create();

        assertNotNull(actual);
        assertEquals("Book Title", actual.getTitle());
        assertEquals(1, actual.getAuthors().size());
        assertEquals("Lev", actual.getAuthors().get(0).getAuthorName());
        assertEquals("Tolstoy", actual.getAuthors().get(0).getAuthorSurname());
        assertEquals(2023, actual.getPublishedYear());
    }

    @Test
    void printList() {
        AppHelperBook appHelperBook = new AppHelperBook(inputMock, authorServiceMock);
        List<Book> books = List.of(
                new Book("Book One", List.of(new Author("Lev", "Tolstoy")), 2023),
                new Book("Book Two", List.of(new Author("Fyodor", "Dostoevsky")), 2022)
        );
        appHelperBook.printList(books);

        String actualOutput = outMock.toString();
        System.out.println("Actual Output:\n" + actualOutput); // Debug output

        String expectedOutput = "1. Book One. Lev, Tolstoy. 2023\n2. Book Two. Fyodor, Dostoevsky. 2022\n";
        assertEquals(expectedOutput, actualOutput);
    }
}