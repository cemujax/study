package com.test.boot;

import com.test.boot.domain.Book;
import com.test.boot.repository.BookRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookJpaTest {

    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void Book_insert() {
        Book book = Book.builder().title(BOOT_TEST_TITLE).
                publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book);

        assertThat(bookRepository.getOne(book.getIdx()), is(book));
    }

    @Test
    public void BookList_insert_select() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1").
                publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);
        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2").
                publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        List<Book> bookList = bookRepository.findAll();

        assertThat(bookList, hasSize(2));
        assertThat(bookList, contains(book1, book2));
    }

    @Test
    public void BookList_insert_deleteAll() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1").
                publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);
        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2").
                publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        bookRepository.deleteAll();
        assertThat(bookRepository.findAll(), IsEmptyCollection.empty());
    }
}
