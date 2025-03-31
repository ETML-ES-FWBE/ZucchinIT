package ch.etmles.payroll.memberRole;

import ch.etmles.payroll.exceptions.ResourceIDNotFound;
import ch.etmles.payroll.member.Member;
import ch.etmles.payroll.member.MemberRepository;
import ch.etmles.payroll.team.Team;
import ch.etmles.payroll.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleService {
    public static final String RESOURCE_NAME = "memberRole";

    private final MemberRoleRepository memberRoleRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public MemberRoleService(MemberRoleRepository memberRoleRepository, MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRoleRepository = memberRoleRepository;
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    public List<MemberRole> getAll() {
        return memberRoleRepository.findAll();
    }

    public MemberRole getById(Long id) {
        return memberRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceIDNotFound(id, RESOURCE_NAME));
    }

    public MemberRole create(MemberRoleDTO memberRoleDTO) {
        Member member = memberRepository.findById(memberRoleDTO.getMemberId())
                .orElseThrow(() -> new ResourceIDNotFound(memberRoleDTO.getMemberId(), "member"));
        Team team = teamRepository.findById(memberRoleDTO.getTeamId())
                .orElseThrow(() -> new ResourceIDNotFound(memberRoleDTO.getTeamId(), "team"));

        MemberRole memberRole = new MemberRole(memberRoleDTO.getRole(), member, team);
        return memberRoleRepository.save(memberRole);
    }

    public MemberRole update(Long id, MemberRoleDTO memberRoleDTO) {
        return memberRoleRepository.findById(id)
                .map(existingMemberRole -> {
                    Member member = memberRepository.findById(memberRoleDTO.getMemberId())
                            .orElseThrow(() -> new ResourceIDNotFound(memberRoleDTO.getMemberId(), "member"));
                    Team team = teamRepository.findById(memberRoleDTO.getTeamId())
                            .orElseThrow(() -> new ResourceIDNotFound(memberRoleDTO.getTeamId(), "team"));

                    existingMemberRole.setRole(memberRoleDTO.getRole());
                    existingMemberRole.setMember(member);
                    existingMemberRole.setTeam(team);
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
