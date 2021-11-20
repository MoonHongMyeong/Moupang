package moon.numble.moupang.address.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.dto.ShippingAddressUpdateRequestDto;
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
    private String addressDetail;

    @Column
    private String zonecode;

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
    private String roadname;

    @Column
    private String roadnameEnglish;

    @Column
    private String bname;

    @Column
    private String bnameEnglish;

    @Builder
    public ShippingAddress(User user, String name, ShippingMain main, String addressDetail, String zonecode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadname, String roadnameEnglish, String bname, String bnameEnglish) {
        this.user=user;
        this.name = name;
        this.main = main;
        this.addressDetail=addressDetail;
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

    public void updateName(String name){
        this.name=name;
    }

    public void doMain(){
        this.main = ShippingMain.MAIN;
    }

    public void cancelMain(){
        this.main = ShippingMain.NOT_MAIN;
    }

    public void updateAddress(String addressDetail, String zonecode, String address, String addressEnglish, String addressType, String userSelectedType, String roadAddress, String roadAddressEnglish, String jibunAddress, String jibunAddressEnglish, String buildingCode, String buildingName, String apartment, String sido, String sidoEnglish, String sigungu, String sigunguEnglish, String roadname, String roadnameEnglish, String bname, String bnameEnglish) {
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

    public void updateAddress(ShippingAddressUpdateRequestDto dto){
        this.main =dto.getMain();
        this.addressDetail = dto.getAddressDetail();
        this.zonecode = dto.getZonecode();
        this.address = dto.getAddress();
        this.addressEnglish = dto.getAddressEnglish();
        this.addressType = dto.getAddressType();
        this.userSelectedType = dto.getUserSelectedType();
        this.roadAddress = dto.getRoadAddress();
        this.roadAddressEnglish = dto.getAddressEnglish();
        this.jibunAddress = dto.getJibunAddress();
        this.jibunAddressEnglish = dto.getJibunAddressEnglish();
        this.buildingCode = dto.getBuildingCode();
        this.buildingName = dto.getBuildingName();
        this.apartment = dto.getApartment();
        this.sido = dto.getSido();
        this.sidoEnglish = dto.getSidoEnglish();
        this.sigungu = dto.getSigunguEnglish();
        this.sigunguEnglish = dto.getSigunguEnglish();
        this.roadname = dto.getRoadname();
        this.roadnameEnglish = dto.getRoadnameEnglish();
        this.bname = dto.getBname();
        this.bnameEnglish = dto.getBnameEnglish();
    }
}
