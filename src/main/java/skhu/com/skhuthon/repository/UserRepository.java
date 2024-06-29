package skhu.com.skhuthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skhu.com.skhuthon.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginIdAndPw(String id, String pw);

    User findByLoginId(String id);
}
