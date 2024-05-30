package com.membertest.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //하나씩 추가
    @Column(name ="member_id")
    private Long id;

    private String name;
    private int age;

    private String phone;
    private String address;

    //Ctrl+d 줄복사
    //Ctrl+y 줄삭제.....( 줄삭제 or read)
    //shift + delete
}
