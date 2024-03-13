package org.example.model;

import com.jfoenix.controls.JFXButton;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTm {
    private String userId;
    private String userName;
    private String name;
    private String email;
    private JFXButton modButton;
    private JFXButton detButton;
}
