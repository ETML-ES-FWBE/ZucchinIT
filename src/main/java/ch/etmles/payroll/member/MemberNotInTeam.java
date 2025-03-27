package ch.etmles.payroll.member;

public class MemberNotInTeam extends RuntimeException {
    public MemberNotInTeam(Long id) {
        super("Member with id " + id + " not in a team");
    }
}
