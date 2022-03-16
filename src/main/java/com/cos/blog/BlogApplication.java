package com.cos.blog;

import com.cos.blog.model.User;
import com.cos.blog.test.HttpControllerTest;
import com.cos.blog.test.TempControllerTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        run(BlogApplication.class, args);
    }

}
