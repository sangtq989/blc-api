package com.project.blockchainapi.repo;

import com.project.blockchainapi.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findUserInfoByEmail(String email);

    @Query("select count(*) > 0 from UserInfo u where u.email = :email and u.isEnable = true")
    boolean existsByEmail(String email);

    @Query("select u from UserInfo u")
    List<UserInfo> getUsersForExpertDashboard();
}
