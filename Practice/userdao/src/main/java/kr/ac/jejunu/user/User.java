package kr.ac.jejunu.user;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="userinfo")
public class User {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY) //자동증가
    private Integer id;
    private String name;
    private String password;

    }
