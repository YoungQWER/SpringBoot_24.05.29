package com.livecommerce.entity;

import com.livecommerce.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;     // 사용자 ID

    private String name;    // 사용자 이름

    @Column(unique = true)      // 중복된 값 허용X
    private String email;   // 사용자 이메일

    private String password;    // 사용자 비밀번호

    private String shippingAddress;     // 배송 주소

    private String shippingPostalCode;  // 배송 우편번호

    @Enumerated(EnumType.STRING)
    private Role role;      // 권한

}
