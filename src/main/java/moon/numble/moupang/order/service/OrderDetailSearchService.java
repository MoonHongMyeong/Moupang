package moon.numble.moupang.order.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailSearchService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail getOrderDetailById(Long orderDetailId) {
        return orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new EntityNotFoundException("주문 상세 번호 : " + orderDetailId.toString()));
    }
}
