package com.project.blockchainapi.repo;

import com.project.blockchainapi.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {

    @Query("select mt from Metadata mt where mt.user.email = :email")
    List<Metadata> getMetadataByUserId(String email);
    @Query("select mt from Metadata mt where mt.user.email = :email and mt.formKey = :metadataKey")
    List<Metadata> getMetadataByKeyAndUserId(String metadataKey, String email);
    @Query(value = "select * from metadata m " +
            "join (select object_id " +
            "       from metadata " +
            "       where form_field_key = 'isCurrentJob' " +
            "       and field_value = 'true') m2 " +
            "on m.object_id = m2.object_id " +
            "join users_info ui on m.user_id = ui.id " +
            "where m.form_key = 'EXPERIENCE' and ui.email = :email", nativeQuery = true)
    Optional<List<Metadata>> getMetadataForSummaryProfile(String email);

    @Query(value = "select * from metadata m where user_id = :userId and form_field_key in ('skills','yearOfExp') order by id desc limit 2", nativeQuery = true)
    Optional<List<Metadata>> getMetadataForSummaryProfileSpecialty(Long userId);

}
