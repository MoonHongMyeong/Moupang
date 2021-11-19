package moon.numble.moupang.address.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShippingMain {
    MAIN, NOT_MAIN;
    private String isMain;
}
