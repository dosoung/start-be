package kr.ac.jejunu.userdao;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
//    private User user  사용하면 안된다 User가 다시 user를 참조하고 계속해서 리커시브 하게 참조하므로 스택오버 플로우 발생
}