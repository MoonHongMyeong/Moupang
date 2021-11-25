package moon.numble.moupang.order.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStep {
    CREATE, ORDER, COMPLETE, CANCEL;
    private String step;
}
