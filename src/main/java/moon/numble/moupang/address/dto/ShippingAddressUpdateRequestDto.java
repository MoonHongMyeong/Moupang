package moon.numble.moupang.address.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingMain;

@Getter
@NoArgsConstructor
public class ShippingAddressUpdateRequestDto {
    private String name;
    private ShippingMain main;
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
}
