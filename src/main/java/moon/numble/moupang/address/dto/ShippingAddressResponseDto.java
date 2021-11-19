package moon.numble.moupang.address.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.user.domain.entity.User;

@Getter
@NoArgsConstructor
public class ShippingAddressResponseDto {

    private Long id;
    private User user;
    private String name;
    private ShippingMain main;
    private String zoneCode;
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
    private String roadNameCode;
    private String roadName;
    private String roadNameEnglish;
    private String bName;
    private String bNameEnglish;

    @Builder
    public ShippingAddressResponseDto(Long id, User user, String name, ShippingMain main, String zoneCode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadNameCode, String roadName, String roadNameEnglish, String bName, String bNameEnglish) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.main = main;
        this.zoneCode = zoneCode;
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
        this.roadNameCode = roadNameCode;
        this.roadName = roadName;
        this.roadNameEnglish = roadNameEnglish;
        this.bName = bName;
        this.bNameEnglish = bNameEnglish;
    }

    public static ShippingAddressResponseDto of(ShippingAddress shippingAddress){
        return ShippingAddressResponseDto.builder()
                .id(shippingAddress.getId())
                .user(shippingAddress.getUser())
                .name(shippingAddress.getName())
                .main(shippingAddress.getMain())
                .zoneCode(shippingAddress.getZoneCode())
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
                .roadName(shippingAddress.getRoadName())
                .roadNameEnglish(shippingAddress.getRoadNameEnglish())
                .bName(shippingAddress.getBName())
                .bNameEnglish(shippingAddress.getBNameEnglish())
                .build();
    }
}
