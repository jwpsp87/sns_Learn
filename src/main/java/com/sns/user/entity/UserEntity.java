package com.sns.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginId;
    private String password;
    private String name;
    private String email;

    @CreationTimestamp
    @Column(name = "createdAt")
    private String createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private String updatedAt;


}
