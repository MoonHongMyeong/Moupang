package moon.numble.moupang.user.domain.repository;

import moon.numble.moupang.user.domain.entity.Membership;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUser(User user);
}
