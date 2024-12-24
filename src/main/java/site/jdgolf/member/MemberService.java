package site.jdgolf.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberRequest request) {
        Member member = new Member(request.getId(), request.getName(), request.getPhoneNumber());

        memberRepository.save(member);
        log.info("Member [{} / {} / {}] saved.", member.getId(), member.getName(),
            member.getPhoneNumber());
    }

    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId)
            .orElse(null);
    }
}
