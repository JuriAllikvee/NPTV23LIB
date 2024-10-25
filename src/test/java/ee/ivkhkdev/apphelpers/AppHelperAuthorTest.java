package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppHelperAuthorTest {
    Input inputMock;
    PrintStream outDefault;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        outDefault = System.out;
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(outDefault);
        outMock = null;
    }

    @Test
    void create() {
        when(inputMock.getString()).thenReturn("Lev", "Tolstoy");
        AppHelperAuthor appHelperAuthor = new AppHelperAuthor(inputMock);
        Author actual = appHelperAuthor.create();
        Author expected = new Author("Lev", "Tolstoy");
        assertEquals(expected.getAuthorName(), actual.getAuthorName());
        assertEquals(expected.getAuthorSurname(), actual.getAuthorSurname());
    }
}
