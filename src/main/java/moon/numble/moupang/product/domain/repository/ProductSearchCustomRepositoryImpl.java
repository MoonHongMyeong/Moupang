package moon.numble.moupang.product.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.product.domain.entity.Company;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.RocketShipping;
import moon.numble.moupang.product.dto.SearchProductCondition;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static moon.numble.moupang.category.domain.entity.QCategory.category;
import static moon.numble.moupang.product.domain.entity.QProduct.product;

@Transactional(readOnly = true)
public class ProductSearchCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductSearchCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ProductSearchCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory=queryFactory;
    }

    @Override
    public List<Product> getAll() {
        return queryFactory.selectFrom(product)
                .orderBy(product.id.desc())
                .fetch();
    }

    @Override
    public List<Product> search(SearchProductCondition condition) {

        BooleanBuilder builder = new BooleanBuilder();
        if(condition.getKeyword() != null){
            builder.and(product.title.contains(condition.getKeyword()));
        }

        if(condition.getMaker() != null){
            builder.and(product.company.eq(Company.valueOf(condition.getMaker())));
        }

        if(condition.getCategoryId() != null){
            builder.and(product.type.id.eq(Long.valueOf(condition.getCategoryId())));
        }

        if(condition.getMinPrice() != null){
            builder.and(product.price.goe(condition.getMinPrice()));
        }

        if(condition.getMaxPrice() != null){
            builder.and(product.price.loe(condition.getMaxPrice()));
        }

        if(condition.getIsRocketShipping() != null){
            builder.and(product.isRocketShipping.eq(RocketShipping.ROCKET_SHIPPING));
        }

        return queryFactory.selectFrom(product)
                .where(builder)
                .join(product.type, category)
                .orderBy(sort(condition.getSort()))
                .fetch();
    }

    private OrderSpecifier<?> sort(String sort) {
        switch (sort){
            case "highToLow":
                return product.price.desc();
            case "lowToHigh":
                return product.price.asc();
            case "date":
                return product.modifiedDate.desc();
            case "salesVolume":
                return product.salesVolume.desc();
//            case "review":
//               return 미구현
            default:
                return product.id.desc();
        }
    }
}
