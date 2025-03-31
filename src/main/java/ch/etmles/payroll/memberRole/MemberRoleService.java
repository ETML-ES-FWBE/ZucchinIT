package ch.etmles.payroll.memberRole;

import ch.etmles.payroll.exceptions.ResourceIDNotFound;
import ch.etmles.payroll.member.Member;
import ch.etmles.payroll.member.MemberNotInTeam;
import ch.etmles.payroll.member.MemberRepository;
import ch.etmles.payroll.member.MemberService;
import ch.etmles.payroll.team.Team;
import ch.etmles.payroll.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleService {
    public static final String RESOURCE_NAME = "memberRole";

    private final MemberRoleRepository memberRoleRepository;
    private final MemberService memberService;

    public MemberRoleService(MemberRoleRepository memberRoleRepository, MemberService memberService) {
        this.memberRoleRepository = memberRoleRepository;
        this.memberService = memberService;
    }

    public List<MemberRole> getAll() {
        return memberRoleRepository.findAll();
    }

    public MemberRole getById(Long id) {
        return memberRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceIDNotFound(id, RESOURCE_NAME));
    }

    public MemberRole create(MemberRoleDTO memberRoleDTO) {
        Member member = memberService.getById(memberRoleDTO.getMemberId());
        if (member.getTeam() == null) throw new MemberNotInTeam(member.getId());

        MemberRole memberRole = new MemberRole(memberRoleDTO.getRole(), member, member.getTeam());
        return memberRoleRepository.save(memberRole);
    }

    public MemberRole update(Long id, MemberRoleDTO memberRoleDTO) {
        return memberRoleRepository.findById(id)
                .map(existingMemberRole -> {
                    Member member = memberService.getById(memberRoleDTO.getMemberId());
                    if (member.getTeam() == null) throw new MemberNotInTeam(member.getId());

                    existingMemberRole.setRole(memberRoleDTO.getRole());
                    existingMemberRole.setMember(member);
                    existingMemberRole.setTeam(member.getTeam());
                    return memberRoleRepository.save(existingMemberRole);
                })
                .orElseThrow(() -> new ResourceIDNotFound(id, RESOURCE_NAME));
    }

    public void delete(Long id) {
        if (memberRoleRepository.existsById(id)) {
            memberRoleRepository.deleteById(id);
        } else {
            throw new ResourceIDNotFound(id, RESOURCE_NAME);
        }
    }
}
