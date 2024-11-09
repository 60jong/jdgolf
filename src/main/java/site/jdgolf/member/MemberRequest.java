package site.jdgolf.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {
    private Integer id;
    private String name;
    private String phoneNumber;

    public Member toEntity() {
        return new Member(this.id, this.name, this.phoneNumber);
    }
}
