package com.project.blockchainapi.repo;

import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.repo.projection.UserDashboardProjection;
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

    @Query(value = "SELECT ui.email as email," +
            "       ui.first_name as firstName," +
            "       ui.last_name as lastName," +
            "       ui.block_chain_address as blockChainAddress," +
            "  (SELECT m2.field_value" +
            "   FROM metadata m2" +
            "   WHERE m2.form_field_key = 'positionName'" +
            "     AND m2.user_id = ui.id ) AS positionName," +
            "  (SELECT m3.field_value" +
            "   FROM metadata m3" +
            "   WHERE m3.form_field_key = 'specialities'" +
            "     AND m3.user_id = ui.id" +
            "   ORDER BY m3.id DESC" +
            "   LIMIT 1) AS specialities" +
            " FROM users_info ui" +
            " JOIN metadata m ON ui.id = m.user_id" +
            " WHERE ui.block_chain_address like '0x%'" +
            "  AND m.field_value like CONCAT('%',:keyword,'%')" +
            " GROUP BY ui.id LIMIT 30", nativeQuery = true)
    List<UserDashboardProjection> getUsersForExpertDashboard(String keyword);
}
