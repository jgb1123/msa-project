package com.example.memberservice.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPostDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")
    private String email;
    @NotBlank
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "비밀번호는 영문, 숫자 조합이어야 합니다.")
    private String password;
    @NotBlank
    @Pattern(regexp = "[가-힣]+$", message = "이름은 한글로만 입력되어야 합니다.")
    private String name;
}
