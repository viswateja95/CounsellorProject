package in.counsellor.repository;

import in.counsellor.entitty.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
    Optional<Counsellor> findByEmail(String email);
    Optional<Counsellor> findByEmailAndPwd(String email, String pwd);
}
