package com.project.blockchainapi.repo;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.entity.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
    @Query("SELECT new Map(ff.formFieldKey, ff.id) FROM FormField ff WHERE ff.form.formKey = :formKey")
    List<Map<String, String>> getFormFieldKeyToMap(@Param("formKey") FormType formKey);
}
