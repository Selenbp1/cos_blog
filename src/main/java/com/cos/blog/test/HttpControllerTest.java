package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private  static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m1 = new Member(1, "cos", "1234", "email");
        Member m = Member.builder().username("cos").password("1234").email("email").build();    //builder 사용 - 순서 상관 없음
        System.out.println(TAG+"getter : "+ m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter : "+m.getId());
        return "lombok test 완료";
    }

    //http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m){    //id=1&username=cos&password=1234&email=cos@gmail.com
        return "get 요청 : "+ m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    //http://localhost:8080/http/post (insert)
    /*@PostMapping("/http/post")
    public String postTest(Member m){
        return "post 요청 : "+ m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }*/

/*    @PostMapping("/http/post")
    public String postTest(@RequestBody String text){   //text/plain
        return "post 요청 : "+text;
    }*/

    @PostMapping("/http/post")  //MessageConverter(스프링부트)
    public String postTest(@RequestBody Member m){   //application/json
        return "post 요청 : "+ m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    //http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
