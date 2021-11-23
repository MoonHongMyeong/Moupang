package moon.numble.moupang.user.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IsMembership {
    MEMBERSHIP, NON_MEMBERSHIP;
    private String isMembership;
}
