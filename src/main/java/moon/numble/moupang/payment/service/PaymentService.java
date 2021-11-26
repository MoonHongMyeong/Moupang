package moon.numble.moupang.payment.service;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import moon.numble.moupang.delivery.service.DeliveryService;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.order.domain.repository.OrderRepository;
import moon.numble.moupang.order.exception.OrderNotFoundException;
import moon.numble.moupang.payment.domain.entity.Payment;
import moon.numble.moupang.payment.domain.entity.PaymentType;
import moon.numble.moupang.payment.domain.repository.PaymentRepository;
import moon.numble.moupang.payment.dto.PaymentResponseDto;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final DeliveryService deliveryService;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional(rollbackFor = Exception.class)
    public PaymentResponseDto pay(Long orderId, User user, String paymentType) {

        ProductOrder order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(orderId.toString()));

        Payment payment = paymentRepository.save(Payment.builder()
                .order(order)
                .user(user)
                .paymentType(PaymentType.valueOf(paymentType))
                .build());

        order.paymentComplete();

        deliveryService.startDelivery(order, user);

        return PaymentResponseDto.of(payment);
    }
}
