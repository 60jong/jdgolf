package site.jdgolf.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private String memberName;
    private Integer memberId;
}
