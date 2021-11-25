package moon.numble.moupang.product.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.domain.repository.ProductSearchCustomRepository;
import moon.numble.moupang.product.dto.ProductListResponseDto;
import moon.numble.moupang.product.dto.SearchProductCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductRepository productSearchCustomRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> search(SearchProductCondition dto) {
        return productSearchCustomRepository.search(dto).stream()
                .map(product -> ProductListResponseDto.of(product))
                .collect(Collectors.toList());
    }
}
