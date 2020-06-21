package kr.ac.jejunu.user;

import kr.ac.jejunu.user.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LombokTests {

    @Test
    public void equals() {
        User user = User.builder().id(1).name("dohyun").password("1234").build();
        User user2 = User.builder().id(1).name("dohyun").password("1234").build();
        assertThat(user, is(user2));
    }
}
