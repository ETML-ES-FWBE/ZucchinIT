package ch.etmles.payroll.memberRole;

public class MemberRoleDTO {

    private MemberRole.PlayerRole role;
    private Long memberId;

    private Long teamId;

    public MemberRoleDTO() {}

    public MemberRoleDTO(MemberRole.PlayerRole role, Long memberId, Long teamId) {
        this.role = role;
        this.memberId = memberId;
        this.teamId = teamId;
    }

    public MemberRole.PlayerRole getRole() {
        return role;
    }

    public void setRole(MemberRole.PlayerRole role) {
        this.role = role;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}