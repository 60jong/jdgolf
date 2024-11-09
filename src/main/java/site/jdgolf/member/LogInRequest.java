package site.jdgolf.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {
    private String phoneNumber;
    private Integer memberId;
}
