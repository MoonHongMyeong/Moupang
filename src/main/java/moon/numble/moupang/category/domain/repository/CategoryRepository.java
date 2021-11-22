package moon.numble.moupang.category.domain.repository;

import moon.numble.moupang.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
