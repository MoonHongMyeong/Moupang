package moon.numble.moupang.address.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.dto.UserResponseDto;

@Getter
@NoArgsConstructor
public class ShippingAddressResponseDto {

    private Long id;
    private UserResponseDto user;
    private String addressName;
    private ShippingMain main;
    private String receiver;
    private String receiverPhone;
    private String deliveryRequest;
    private String enhancePassword;
    private String addressDetail;
    private String zonecode;
    private String address;
    private String addressEnglish;
    private String addressType;
    private String userSelectedType;
    private String roadAddress;
    private String roadAddressEnglish;
    private String jibunAddress;
    private String jibunAddressEnglish;
    private String buildingCode;
    private String buildingName;
    private String apartment;
    private String sido;
    private String sidoEnglish;
    private String sigungu;
    private String sigunguEnglish;
    private String roadnameCode;
    private String roadname;
    private String roadnameEnglish;
    private String bname;
    private String bnameEnglish;

    @Builder
    public ShippingAddressResponseDto(Long id, UserResponseDto user, String addressName, ShippingMain main, String receiver, String receiverPhone, String deliveryRequest, String enhancePassword, String addressDetail, String zonecode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadnameCode, String roadname, String roadnameEnglish, String bname, String bnameEnglish) {
        this.user = user;
        this.addressName = addressName;
        this.main = main;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.deliveryRequest = deliveryRequest;
        this.enhancePassword = enhancePassword;
        this.addressDetail = addressDetail;
        this.zonecode = zonecode;
        this.address = address;
        this.addressEnglish = addressEnglish;
        this.addressType = addressType;
        this.userSelectedType = userSelectedType;
        this.roadAddress = roadAddress;
        this.roadAddressEnglish = roadAddressEnglish;
        this.jibunAddress = jibunAddress;
        this.jibunAddressEnglish = jibunAddressEnglish;
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
        this.apartment = apartment;
        this.sido = sido;
        this.sidoEnglish = sidoEnglish;
        this.sigungu = sigungu;
        this.sigunguEnglish = sigunguEnglish;
        this.roadnameCode = roadnameCode;
        this.roadname = roadname;
        this.roadnameEnglish = roadnameEnglish;
        this.bname = bname;
        this.bnameEnglish = bnameEnglish;
    }

    public static ShippingAddressResponseDto of(ShippingAddress shippingAddress){
        return ShippingAddressResponseDto.builder()
                .id(shippingAddress.getId())
                .user(UserResponseDto.of(shippingAddress.getUser()))
                .addressName(shippingAddress.getAddressName())
                .main(shippingAddress.getMain())
                .receiver(shippingAddress.getReceiver())
                .receiverPhone(shippingAddress.getReceiverPhone())
                .deliveryRequest(shippingAddress.getDeliveryRequest())
                .enhancePassword(shippingAddress.getEnhancePassword())
                .addressDetail(shippingAddress.getAddressDetail())
                .zonecode(shippingAddress.getZonecode())
                .address(shippingAddress.getAddress())
                .addressEnglish(shippingAddress.getAddressEnglish())
                .addressType(shippingAddress.getAddressType())
                .userSelectedType(shippingAddress.getUserSelectedType())
                .roadAddress(shippingAddress.getRoadAddress())
                .roadAddressEnglish(shippingAddress.getRoadAddressEnglish())
                .jibunAddress(shippingAddress.getJibunAddress())
                .jibunAddressEnglish(shippingAddress.getJibunAddressEnglish())
                .buildingCode(shippingAddress.getBuildingCode())
                .buildingName(shippingAddress.getBuildingName())
                .apartment(shippingAddress.getApartment())
                .sido(shippingAddress.getSido())
                .sidoEnglish(shippingAddress.getSidoEnglish())
                .sigungu(shippingAddress.getSigungu())
                .sigunguEnglish(shippingAddress.getSigunguEnglish())
                .roadname(shippingAddress.getRoadname())
                .roadnameEnglish(shippingAddress.getRoadnameEnglish())
                .bname(shippingAddress.getBname())
                .bnameEnglish(shippingAddress.getBnameEnglish())
                .build();
    }
}
