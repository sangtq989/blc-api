package com.project.blockchainapi.repo;

import com.project.blockchainapi.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {

    @Query("select mt from Metadata mt where mt.user.email = :email")
    List<Metadata> getMetadataByUserId(String email);

}
