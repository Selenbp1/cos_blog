package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    //빌더 패턴!!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성됨
//@DynamicInsert // insert 시에 null인 필드를 제외시켜줌
public class User {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id; //시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private  String username; //아이디

    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB는 RoleType이라는게 없음
    @Enumerated(EnumType.STRING)
    private RoleType role;    // Enum을 사용하는게 더 좋음 (ADMIN, USER)

    @CreationTimestamp  //시간이 자동 입력
    private Timestamp createDate;
}
