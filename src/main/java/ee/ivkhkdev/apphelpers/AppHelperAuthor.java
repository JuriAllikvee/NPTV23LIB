package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;

import java.util.List;

public class AppHelperAuthor implements AppHelper<Author> {
    private final Input input;

    public AppHelperAuthor(Input input) {
        this.input = input;
    }

    @Override
    public Author create() {
        try {
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(input.getString());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(input.getString());
            return author;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void printList(List<Author> authors) {
        for (Author author : authors) {
            System.out.println(author.getAuthorName() + " " + author.getAuthorSurname());
        }
    }
}