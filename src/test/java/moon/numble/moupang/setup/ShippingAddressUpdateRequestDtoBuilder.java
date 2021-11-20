package moon.numble.moupang.setup;

import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.address.dto.ShippingAddressUpdateRequestDto;

public class ShippingAddressUpdateRequestDtoBuilder {
    public static ShippingAddressUpdateRequestDto build(){
        return ShippingAddressUpdateRequestDto.builder()
                .name("UpdateAddressShipping")
                .main(ShippingMain.MAIN)
                .zonecode("zoneCode")
                .address("updateAddress")
                .addressEnglish("addressEnglish")
                .addressType("addressType")
                .userSelectedType("userSelectedType")
                .roadAddress("roadAddress")
                .roadAddressEnglish("roadAddressEnglish")
                .jibunAddress("jibunAddress")
                .jibunAddressEnglish("jibunAddressEnglish")
                .buildingCode("buildingCode")
                .buildingName("buildingName")
                .apartment("apartment")
                .sido("sido")
                .sidoEnglish("sidoEnglish")
                .sigungu("sigungu")
                .sigunguEnglish("sigunguEnglish")
                .roadname("roadName")
                .roadnameEnglish("roadNameEnglish")
                .bname("bName")
                .bnameEnglish("bNameEnglish")
                .build();
    }
}
