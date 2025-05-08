package mil.swf.jm_finalproject.repository;

import mil.swf.jm_finalproject.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
