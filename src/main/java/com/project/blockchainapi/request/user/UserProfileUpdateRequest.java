package com.project.blockchainapi.request.user;

import com.project.blockchainapi.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequest {
    @NotEmpty
    private String blockChainAddress;
//    private MultipartFile avatar;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String address;
    private String description;
}
