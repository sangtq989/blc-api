package com.project.blockchainapi.request.form.cert;

import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.request.form.CertificateFormRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CertAwardFormRequest extends CertificateFormRequest {

    @NotBlank
    private String awardName;
    private String awardOrg;
    @NotNull
    private LocalDate publishDate;

    @Builder(builderMethodName = "awardBuilder")
    public CertAwardFormRequest(FormType type) {
        super(type);
    }

    @Override
    public FormType getFormType() {
        return FormType.CERT_AWARD;
    }
}
