package com.test.boot;

import com.test.boot.controller.BookController;
import com.test.boot.domain.Book;
import com.test.boot.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void Book_MVC_Test() throws Exception {
        Book book = new Book("Spring Boot Book", LocalDateTime.now());
        given(bookService.getBookList()).willReturn(Collections.singletonList(book));
        mvc.perform(get("/books"))
                .andExpect(status().isOk()) // HTTP 상태값이 200인지
                .andExpect(view().name("book")) // 반환되는 뷰 이름이 'book'인지
                .andExpect(model().attributeExists("bookList")) // model의 프로퍼티 중 'bookList'라는 프로퍼티 존재하는지
                .andExpect(model().attribute("bookList", contains(book))); // model의 프로퍼티 중 'bookList'에 book 객체가 담겨있는지

    }
}
