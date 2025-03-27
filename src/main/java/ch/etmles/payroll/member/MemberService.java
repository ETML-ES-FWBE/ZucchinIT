package ch.etmles.payroll.member;

import ch.etmles.payroll.exceptions.ResourceIDNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    public static final String RESOURCE_NAME = "member";
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member getById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceIDNotFound(id, RESOURCE_NAME));
    }

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public Member patch(Long id, Map<String, Object> values) {
        Member member = getById(id);

        values.forEach((key, value) -> {
            switch (key) {
                case "id":
                    member.setId(Long.parseLong(value.toString()));
                    break;
                case "firstName":
                    member.setFirstName(value.toString());
                    break;
                case "lastName":
                    member.setLastName(value.toString());
                    break;
                case "email":
                    member.setEmail(value.toString());
                    break;
                case "type":
                    member.setType(Member.MemberType.valueOf(value.toString()));
                    break;
                case "flocking":
                    member.setFlocking(Integer.parseInt(value.toString()));
                case "team_id":
                    // @TODO implement
                    break;
            }
        });

        return memberRepository.save(member);
    }

    public Member update(Long id, Member member) {
        return memberRepository.findById(id)
                .map(existantMember -> {
                    existantMember.setFirstName(member.getFirstName());
                    existantMember.setLastName(member.getLastName());
                    existantMember.setEmail(member.getEmail());
                    existantMember.setType(member.getType());
                    existantMember.setFlocking(member.getFlocking());
                    return memberRepository.save(existantMember);
                })
                .orElseGet(() -> {
                    member.setId(id);
                    return memberRepository.save(member);
                });
    }

    public void delete(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new ResourceIDNotFound(id, RESOURCE_NAME);
        }
    }
}
