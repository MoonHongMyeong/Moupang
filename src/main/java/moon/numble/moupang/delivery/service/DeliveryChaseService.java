package moon.numble.moupang.delivery.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.delivery.domain.repository.DeliveryRepository;
import moon.numble.moupang.delivery.dto.DeliveryResponseDto;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryChaseService {

    private final UserService userService;
    private final DeliveryRepository deliveryRepository;

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("배송 번호가 없습니다."));
    }

    public List<Delivery> getDeliveries(SessionUser sessionUser) {

        User user = userService.getUserToSessionUser(sessionUser);

        return deliveryRepository.getDeliveriesByUser(user);
    }
}
