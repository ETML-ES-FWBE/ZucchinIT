package ch.etmles.payroll.memberRole;

public class MemberRoleDTO {
    private MemberRole.PlayerRole role;
    private Long memberId;

    public MemberRoleDTO() {}

    public MemberRoleDTO(MemberRole.PlayerRole role, Long memberId) {
        this.role = role;
        this.memberId = memberId;
    }

    public MemberRole.PlayerRole getRole() {
        return role;
    }

    public Long getMemberId() {
        return memberId;
    }
}