package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table()
public class User {
    @Id
    private String userId;
    private String name;
    @Column(unique = true)
    private String userName;

    private String email;
    private String passWord;
    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Log> logs;


    public User() {
    }

    public User(String userId, String name, String userName, String email, String passWord) {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
    }

}
