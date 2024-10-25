package ee.ivkhkdev;

import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.Service;

public class App {

    private final AuthorService authorService;
    private final Service<Book> bookService;
    private final Input input;

    public App(Input input, Service<Book> bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.input = input;
        this.authorService = authorService;
    }

    public void run() {
        System.out.println("------ Библиотека группы NPTV23 ------");
        System.out.println("--------------------------------------");
        boolean repeat = true;
        do {
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список книг");
            System.out.println("5. добавить автора");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.getString());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    if (bookService.add()) {
                        System.out.println("Книга добавлена");
                    } else {
                        System.out.println("Книгу добавить не удалось");
                    }
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    bookService.print();
                    break;
                case 5:
                    System.out.println("----- Добавление автора -----");
                    if (authorService.add()) {
                        System.out.println("Автор добавлен");
                    } else {
                        System.out.println("Автора добавить не удалось");
                    }
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("--------------------------------------");
        } while (repeat);
        System.out.println("До свидания :)");
    }
}