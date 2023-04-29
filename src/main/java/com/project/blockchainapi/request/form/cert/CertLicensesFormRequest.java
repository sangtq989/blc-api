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
public class CertLicensesFormRequest extends CertificateFormRequest {
    @NotBlank
    private String licensesName;
    @NotBlank
    private String publisher;
    @NotNull
    private LocalDate publishFrom;
    @NotNull
    private LocalDate publishTo;
    @NotBlank
    private String licensesCode;
    @NotBlank
    private String licensesUrl;

    @Builder(builderMethodName = "licensesBuilder")
    public CertLicensesFormRequest(FormType type) {
        super(type);
    }

    @Override
    public FormType getFormType() {
        return FormType.CERT_LICENSES;
    }
}
