package com.project.blockchainapi.request.form;

import com.project.blockchainapi.constant.FormType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceFormRequest implements BaseFormRequest{
    @NotBlank
    private String positionName;
    @NotBlank
    private String employeeType;
    @NotBlank
    private String companyName;
    private String companyAddress;
    private Boolean isCurrentJob;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobDesc;

    @Override
    public FormType getFormType() {
        return FormType.EXPERIENCE;
    }
}
