package com.project.blockchainapi.request.form.cert;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.request.form.CertificateFormRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CertCourseFormRequest extends CertificateFormRequest {

    @NotBlank
    private String courseName;
    @NotBlank
    private String courseProvider;

    @Builder(builderMethodName = "courseBuilder")
    public CertCourseFormRequest(FormType type) {
        super(type);
    }

    @Override
    public FormType getFormType() {
        return FormType.CERT_COURSE;
    }
}
