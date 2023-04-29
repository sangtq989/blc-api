package com.project.blockchainapi.request.form;


import com.project.blockchainapi.constant.FormType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityFormRequest implements BaseFormRequest{

    @NotEmpty
    private List<String> specialities;

    @NotNull
    private Integer yearOfExp;
    private List<String> skills;
    private List<String> languages;

    @Override
    public FormType getFormType() {
        return FormType.SPECIALTY;
    }
}
