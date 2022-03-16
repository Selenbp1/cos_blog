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
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    //대용량 데이터 사용할 때 사용
    private String content; //섬머노트 라이브러리 사용. <html>태그가 섞여서 디자인 됨

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne  //board = many, One = User
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없음. FK, 자바는 오브젝트를 저장할 수 있음

    @CreationTimestamp
    private Timestamp createDate;
}
