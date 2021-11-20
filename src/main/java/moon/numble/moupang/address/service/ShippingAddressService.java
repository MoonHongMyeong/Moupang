package moon.numble.moupang.address.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.address.domain.repository.ShippingAddressCustomRepository;
import moon.numble.moupang.address.domain.repository.ShippingAddressRepository;
import moon.numble.moupang.address.dto.ShippingAddressResponseDto;
import moon.numble.moupang.address.dto.ShippingAddressSaveRequestDto;
import moon.numble.moupang.address.dto.ShippingAddressUpdateRequestDto;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {
    private final ShippingAddressCustomRepository addressCustomRepository;
    private final ShippingAddressRepository addressRepository;

    public List<ShippingAddressResponseDto> getAddresses(User user) {
        return addressCustomRepository.findAllUserAddress(user)
                .stream()
                .map(shippingAddress -> ShippingAddressResponseDto.of(shippingAddress))
                .collect(Collectors.toList());
    }

    public ShippingAddressResponseDto create(User user, ShippingAddressSaveRequestDto addressSaveRequestDto) {

        ShippingAddress shippingAddress =
                addressRepository.save(addressSaveRequestDto.toEntity(user));

        return ShippingAddressResponseDto.of(shippingAddress);
    }

    private List<ShippingAddress> getAllEntity(User user){
        return addressCustomRepository.findAllUserAddress(user);
    }

    public void allCancelMain(User user) {

        getAllEntity(user).stream()
                .filter(shippingAddress -> shippingAddress.getMain() == ShippingMain.MAIN)
                .forEach(shippingAddress -> shippingAddress.cancelMain());
    }

    public ShippingAddressResponseDto update(Long addressId, ShippingAddressUpdateRequestDto updateDto) {

        ShippingAddress shippingAddress = addressRepository.getById(addressId);
        shippingAddress.updateAddress(updateDto);

        return ShippingAddressResponseDto.of(shippingAddress);
    }

    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
