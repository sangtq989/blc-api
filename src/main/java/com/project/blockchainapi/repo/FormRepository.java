package com.project.blockchainapi.repo;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    @Query("select f from Form f where f.formKey = :formKey")
    Optional<Form> getFormByFormKey(FormType formKey);

}
