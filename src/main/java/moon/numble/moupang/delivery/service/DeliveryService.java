package moon.numble.moupang.delivery.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.delivery.domain.repository.DeliveryRepository;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.product.domain.entity.RocketShipping;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void startDelivery(ProductOrder order, User user){

        List<OrderDetail> details = order.getOrderDetails();

        for (OrderDetail orderDetail : details) {
            if (orderDetail.getCart().getProduct().getIsRocketShipping().equals(RocketShipping.ROCKET_SHIPPING)) {
                deliveryRepository.save(Delivery.builder()
                        .orderDetail(orderDetail)
                        .user(user)
                        .completeDate(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(6, 0, 0)))
                        .build());
            } else {
                deliveryRepository.save(Delivery.builder()
                        .orderDetail(orderDetail)
                        .user(user)
                        .completeDate(LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.now()))
                        .build());
            }
        }

        // 자 봐라 정리한다.
        // 물품을 생성할 때 로켓배송 유무 맞는가? <- 난 맞다고 본다
        // 카트에 물품을 담을 때 물품을 가져오지 외래키로 <- 그래서 카트에서 접근이 가능함.
        // 카트에서 화면에 보여줄 때, 로켓배송이랑 판매자 배송 나눠줄 수 있어.
        // >> cart.product.isRocketShipping 뭐 이런식으로 값이 있으면 리스트, 없으면 판매자 배송에 리스트
        // 주문을 해 카트에서 로켓배송 판매자 배송 가리지 않고 같이 할 수 있단말야? <- 이거 맞지?
        // 그러면 결제도 같이하지 <- 이것도 맞지
        // 근데 로켓배송은 배송시간이 23:59:59 전에 결제완료했으면 익일 06:00:00 으로 배송완료 할 수 있게 해달라고 했단말이지
        // 근데 판매자배송은 3일을 걸린다고 하라고 했어.
        // 그러면 주문에서 주문디테일을 분할해서 배송에 집어넣어.
        // >> 오더 디테일하고 유저하고 시간이 필요하네
        // 그 다음에 배송 추적할 때는 유저를 기준으로 불러와서 화면에 보여주면 되네
        // 배송완료되면 리뷰쓰기 기능 되게 해주면 되고 그건 프론트에서 현재 시간과 저장된 컴플리트 타임과 비교해서
        // >> 시간이 작으면 배송중, 아니면 배송완료
    }
}
