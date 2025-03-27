package ch.etmles.payroll.member;

import jakarta.persistence.*;

import java.util.Objects;

// @TODO Add team property
@Entity
public class Member {
    public enum MemberType {
        ATTACKER,
        MIDFIELDER,
        DEFENDER,
        GOALKEEPER
    }

    @Id @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private Integer flocking;


    public Member() {}
    public Member(Long id, String firstName, String lastName, String email, MemberType type, Integer flocking) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setType(type);
        this.setFlocking(flocking);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public MemberType getType() { return type; }
    public void setType(MemberType memberType) { this.type = memberType; }

    public Integer getFlocking() { return flocking; }
    public void setFlocking(Integer flocking) { this.flocking = flocking; }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Member member))
            return false;
        return Objects.equals(this.id, member.id)
                && Objects.equals(this.firstName, member.firstName)
                && Objects.equals(this.lastName, member.lastName)
                && Objects.equals(this.email, member.email)
                && Objects.equals(this.type, member.type)
                && Objects.equals(this.flocking, member.flocking);
        // @TODO Add team
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.type, this.flocking);
        // @TODO Add team
    }
}
