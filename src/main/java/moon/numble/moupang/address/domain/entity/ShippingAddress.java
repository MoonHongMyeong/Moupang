package moon.numble.moupang.address.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ShippingAddress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "isMain")
    private ShippingMain main;

    @Column
    private String zoneCode;

    @Column
    private String address;

    @Column
    private String addressEnglish;

    @Column
    private String addressType;

    @Column
    private String userSelectedType;

    @Column
    private String roadAddress;

    @Column
    private String roadAddressEnglish;

    @Column
    private String jibunAddress;

    @Column
    private String jibunAddressEnglish;

    @Column
    private String buildingCode;

    @Column
    private String buildingName;

    @Column
    private String apartment;

    @Column
    private String sido;

    @Column
    private String sidoEnglish;

    @Column
    private String sigungu;

    @Column
    private String sigunguEnglish;

    @Column
    private String roadNameCode;

    @Column
    private String roadName;

    @Column
    private String roadNameEnglish;

    @Column
    private String bName;

    @Column
    private String bNameEnglish;

    @Builder
    public ShippingAddress(User user, String name, ShippingMain main, String zoneCode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadNameCode, String roadName, String roadNameEnglish, String bName, String bNameEnglish) {
        this.user=user;
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

    public void updateName(String name){
        this.name=name;
    }

    public void doMain(){
        this.main = ShippingMain.MAIN;
    }

    public void cancelMain(){
        this.main = ShippingMain.NOT_MAIN;
    }

    public void updateAddress(String zoneCode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadNameCode, String roadName, String roadNameEnglish, String bName, String bNameEnglish) {
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
}
