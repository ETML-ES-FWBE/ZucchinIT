package ch.etmles.payroll.member;

public class MemberAlreadyInTeam extends RuntimeException {
    public MemberAlreadyInTeam(Long id) {
        super("Member with id " + id + " already in a team");
    }
}
