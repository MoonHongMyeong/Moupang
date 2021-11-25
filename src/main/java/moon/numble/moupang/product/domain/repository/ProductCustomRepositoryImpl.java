package moon.numble.moupang.product.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.product.domain.entity.Product;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static moon.numble.moupang.category.domain.entity.QCategory.category;
import static moon.numble.moupang.product.domain.entity.QClothesOption.clothesOption;
import static moon.numble.moupang.product.domain.entity.QProduct.product;
import static moon.numble.moupang.product.domain.entity.QProductOption.productOption;

public class ProductCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ProductCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Product findProduct(Long productId) {
        return queryFactory.selectFrom(product)
                .leftJoin(product.productOptions, productOption)
                .leftJoin(product.type, category)
                .leftJoin(product.clothesOption, clothesOption)
                .fetchJoin()
                .where(productOption.product.id.eq(productId))
                .fetchOne();
    }
}
