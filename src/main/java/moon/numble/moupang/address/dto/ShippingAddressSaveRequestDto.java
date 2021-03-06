package moon.numble.moupang.address.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.user.domain.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ShippingAddressSaveRequestDto {
    @NotNull
    private User user;
    @NotEmpty
    @Length(message = "배송지 이름은 한 글자 이상 입력해야 합니다.", min = 1)
    private String addressName;
    @NotNull
    private ShippingMain main;
    @NotEmpty
    private String receiver;
    @NotEmpty
    private String receiverPhone;
    @NotNull
    private String deliveryRequest;
    @NotNull
    private String enhancePassword;
    @NotNull
    private String addressDetail;
    @NotNull
    private String zonecode;
    @NotNull
    private String address;
    @NotNull
    private String addressEnglish;
    @NotNull
    private String addressType;
    @NotNull
    private String userSelectedType;
    @NotNull
    private String roadAddress;
    @NotNull
    private String roadAddressEnglish;
    @NotNull
    private String jibunAddress;
    @NotNull
    private String jibunAddressEnglish;
    @NotNull
    private String buildingCode;
    @NotNull
    private String buildingName;
    @NotNull
    private String apartment;
    @NotNull
    private String sido;
    @NotNull
    private String sidoEnglish;
    @NotNull
    private String sigungu;
    @NotNull
    private String sigunguEnglish;
    @NotNull
    private String roadname;
    @NotNull
    private String roadnameEnglish;
    @NotNull
    private String bname;
    @NotNull
    private String bnameEnglish;

    @Builder
    public ShippingAddressSaveRequestDto(User user, String addressName, ShippingMain main, String receiver, String receiverPhone, String deliveryRequest, String enhancePassword, String addressDetail, String zonecode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadname, String roadnameEnglish, String bname, String bnameEnglish) {
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
        this.roadname = roadname;
        this.roadnameEnglish = roadnameEnglish;
        this.bname = bname;
        this.bnameEnglish = bnameEnglish;
    }

    public ShippingAddress toEntity(User user){
        return ShippingAddress.builder()
                .user(user)
                .addressName(this.addressName)
                .main(this.main)
                .receiver(this.receiver)
                .receiverPhone(this.receiverPhone)
                .deliveryRequest(this.deliveryRequest)
                .entrancePassword(this.enhancePassword)
                .addressDetail(this.addressDetail)
                .zonecode(this.zonecode)
                .address(this.address)
                .addressEnglish(this.addressEnglish)
                .addressType(this.addressType)
                .userSelectedType(this.userSelectedType)
                .roadAddress(this.roadAddress)
                .roadAddressEnglish(this.roadAddressEnglish)
                .jibunAddress(this.jibunAddress)
                .jibunAddressEnglish(this.jibunAddressEnglish)
                .buildingCode(this.buildingCode)
                .buildingName(this.buildingName)
                .apartment(this.apartment)
                .sido(this.sido)
                .sidoEnglish(this.sidoEnglish)
                .sigungu(this.sigungu)
                .sigunguEnglish(this.sigunguEnglish)
                .roadname(this.roadname)
                .roadnameEnglish(this.roadnameEnglish)
                .bname(this.bname)
                .bnameEnglish(this.bnameEnglish)
                .build();
    }
}
