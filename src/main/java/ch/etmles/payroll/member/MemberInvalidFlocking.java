package ch.etmles.payroll.member;

public class MemberInvalidFlocking extends RuntimeException {
    public MemberInvalidFlocking(Integer id) {
        super("Flocking " + id + " is not valid");
    }
}
