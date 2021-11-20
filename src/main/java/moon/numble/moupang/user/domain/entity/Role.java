package moon.numble.moupang.user.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    CUSTOMER, SELLER, ADMIN;

    private String role;
}
