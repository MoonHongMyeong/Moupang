package moon.numble.moupang.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.delivery.domain.repository.DeliveryRepository;
import moon.numble.moupang.delivery.dto.DeliveryResponseDto;
import moon.numble.moupang.delivery.service.DeliveryChaseService;
import moon.numble.moupang.user.annotation.LoginRequired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class DeliveryController {
    private DeliveryChaseService deliveryChaseService;

    @GetMapping("/deliveries/{deliveryId}")
    public ResponseEntity<DeliveryResponseDto> getDelivery(@PathVariable("deliveryId") Long deliveryId){

        DeliveryResponseDto response = DeliveryResponseDto.of(deliveryChaseService.getDelivery(deliveryId));

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryResponseDto>> getDeliveries(@LoginUser SessionUser user){

        List<DeliveryResponseDto> response = deliveryChaseService.getDeliveries(user).stream()
                .map(delivery -> DeliveryResponseDto.of(delivery))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
