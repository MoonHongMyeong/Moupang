package moon.numble.moupang.setup;

import moon.numble.moupang.user.domain.entity.User;

public class UserBuilder {
    public static User build(){
        return User.builder()
                .email("testAdmin1@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112222")
                .build();
    }
}
