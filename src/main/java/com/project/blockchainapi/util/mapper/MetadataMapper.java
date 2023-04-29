package com.project.blockchainapi.util.mapper;

import com.project.blockchainapi.config.security.SecurityUtils;
import com.project.blockchainapi.entity.Form;
import com.project.blockchainapi.entity.Metadata;
import com.project.blockchainapi.repo.FormFieldRepository;
import com.project.blockchainapi.repo.FormRepository;
import com.project.blockchainapi.request.form.BaseFormRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class MetadataMapper {

    private final FormRepository formRepository;
    private final FormFieldRepository formFieldRepository;

    public List<Metadata> mapToEntity(BaseFormRequest dto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Metadata> formMetadata = new ArrayList<>();
        UUID objectId = UUID.randomUUID();
        Form form = formRepository.getFormByFormKey(dto.getFormType()).orElseThrow();
        // performance improve since don't need to query the config each loop
        var data = formFieldRepository.getFormFieldKeyToMap(dto.getFormType());
        var combinedMap = new HashMap<>();
        for (var map : data) {
            combinedMap.put(map.get("0"), map.get("1"));
        }
        Class<?> clazz = dto.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            String getterMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            var dtoFieldValue = clazz.getDeclaredMethod(getterMethodName).invoke(dto);
            formMetadata.add(new Metadata("", objectId, dtoFieldValue.toString(), form.getFormKey(), field.getName(), SecurityUtils.currentLogin()));
        }

        return formMetadata;
    }
}
