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
    @NotEmpty
    private User user;
    @NotEmpty
    @Length(message = "배송지 이름은 한 글자 이상 입력해야 합니다.", min = 1)
    private String name;
    @NotEmpty
    private ShippingMain main;
    @NotNull
    private String zoneCode;
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
    private String roadNameCode;
    @NotNull
    private String roadName;
    @NotNull
    private String roadNameEnglish;
    @NotNull
    private String bName;
    @NotNull
    private String bNameEnglish;

    @Builder
    public ShippingAddressSaveRequestDto(User user, String name, ShippingMain main, String zoneCode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadNameCode, String roadName, String roadNameEnglish, String bName, String bNameEnglish) {
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

    public ShippingAddress toEntity(User user){
        return ShippingAddress.builder()
                .user(user)
                .name(this.name)
                .main(this.main)
                .zoneCode(this.zoneCode)
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
                .roadName(this.roadName)
                .roadNameEnglish(this.roadNameEnglish)
                .bName(this.bName)
                .bNameEnglish(this.bNameEnglish)
                .build();
    }
}
