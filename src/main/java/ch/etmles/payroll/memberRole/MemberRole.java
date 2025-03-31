package ch.etmles.payroll.memberRole;

import ch.etmles.payroll.member.Member;
import ch.etmles.payroll.team.Team;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "role", "team_id" }) })
public class MemberRole {
    public enum PlayerRole {
        Captain,
        CornerShooter,
        PenaltyShooter,
        FreeKickShooter
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlayerRole role;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public  MemberRole(){}
    public MemberRole(PlayerRole role, Member member, Team team) {
        this.setRole(role);
        this.setMember(member);
        this.setTeam(team);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof MemberRole memberRole))
            return false;
        return Objects.equals(this.id, memberRole.id)
                && Objects.equals(this.role, memberRole.role)
                && Objects.equals(this.member, memberRole.member)
                && Objects.equals(this.team, memberRole.team);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.role, this.member, this.team);
    }
}
