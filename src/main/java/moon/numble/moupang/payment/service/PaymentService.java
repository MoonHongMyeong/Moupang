package moon.numble.moupang.payment.service;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.order.domain.repository.OrderRepository;
import moon.numble.moupang.order.exception.OrderNotFoundException;
import moon.numble.moupang.payment.domain.entity.Payment;
import moon.numble.moupang.payment.domain.entity.PaymentType;
import moon.numble.moupang.payment.domain.repository.PaymentRepository;
import moon.numble.moupang.payment.dto.PaymentResponseDto;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public PaymentResponseDto pay(Long orderId, User user, String paymentType) {

        Optional<ProductOrder> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new OrderNotFoundException(orderId.toString());
        }

        Payment payment = paymentRepository.save(Payment.builder()
                .order(order.get())
                .user(user)
                .paymentType(PaymentType.valueOf(paymentType))
                .build());

        order.get().paymentComplete();

        return PaymentResponseDto.of(payment);
    }
}
