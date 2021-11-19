package moon.numble.moupang.service;

import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.address.domain.repository.ShippingAddressRepository;
import moon.numble.moupang.address.dto.ShippingAddressResponseDto;
import moon.numble.moupang.address.dto.ShippingAddressSaveRequestDto;
import moon.numble.moupang.address.service.ShippingAddressService;
import moon.numble.moupang.setup.ShippingAddressSaveRequestDtoBuilder;
import moon.numble.moupang.setup.TestProfile;
import moon.numble.moupang.setup.UserBuilder;
import moon.numble.moupang.user.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(TestProfile.TEST)
public class ShippingAddressServiceTest {
    @InjectMocks
    private ShippingAddressService addressService;
    @Mock
    private ShippingAddressRepository addressRepository;

    @Test
    public void 배송지_추가_성공(){
        final User user = UserBuilder.build();
        final ShippingMain main = ShippingMain.MAIN;
        final ShippingAddressSaveRequestDto dto = ShippingAddressSaveRequestDtoBuilder.build(user, main);
        final ShippingAddress address = dto.toEntity(user);

        given(addressRepository.save(any())).willReturn(address);

        final ShippingAddressResponseDto responseDto = addressService.create(user, dto);

        assertNotNull(responseDto);
        assertEquals(responseDto.getAddress(),address.getAddress());
    }

    //배송지_삭제_성공()
}
