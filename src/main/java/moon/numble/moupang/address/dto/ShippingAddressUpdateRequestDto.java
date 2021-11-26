package moon.numble.moupang.address.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingMain;

@Getter
@NoArgsConstructor
public class ShippingAddressUpdateRequestDto {
    private String addressName;
    private String main;
    private String receiver;
    private String receiverPhone;
    private String deliveryRequest;
    private String entrancePassword;
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
    public ShippingAddressUpdateRequestDto(String addressName, String main, String receiver, String receiverPhone, String deliveryRequest, String entrancePassword, String addressDetail, String zonecode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadnameCode, String roadname, String roadnameEnglish, String bname, String bnameEnglish) {
        this.addressName = addressName;
        this.main = main;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.deliveryRequest = deliveryRequest;
        this.entrancePassword = entrancePassword;
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
}
