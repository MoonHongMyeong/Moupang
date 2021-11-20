package moon.numble.moupang.setup;

import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.address.dto.ShippingAddressSaveRequestDto;
import moon.numble.moupang.user.domain.entity.User;

public class ShippingAddressSaveRequestDtoBuilder {
    public static ShippingAddressSaveRequestDto build(User user, ShippingMain main) {
        return ShippingAddressSaveRequestDto.builder()
                .user(user)
                .name("addressShipping")
                .main(main)
                .zonecode("zoneCode")
                .address("address")
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
