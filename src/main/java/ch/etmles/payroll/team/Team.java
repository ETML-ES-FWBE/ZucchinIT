package ch.etmles.payroll.team;

import ch.etmles.payroll.member.Member;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String email;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> memberRoles;

    public Team() {}
    public Team(String name, String address, String city, String zipCode, String email) {
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setZipCode(zipCode);
        this.setEmail(email);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof Team team)){
            return false;
        }
        return Objects.equals(this.id, team.id) &&
                Objects.equals(this.name, team.name)&&
                Objects.equals(this.address, team.address)&&
                Objects.equals(this.city, team.city)&&
                Objects.equals(this.zipCode, team.zipCode)&&
                Objects.equals(this.email, team.email);
    }

    public int hashCode(){
        return Objects.hash(
                this.id,
                this.name,
                this.address,
                this.city,
                this.zipCode,
                this.email);
    }

    public String toString(){
        return "Team {"
                + "id = " + this.getId()
                + ", name='" +  this.getName() + '\''
                + ", address='" +  this.getAddress() + '\''
                + ", city='" +  this.getCity() + '\''
                + ", zipCode='" +  this.getZipCode() + '\''
                + ", email='" +  this.getEmail() + '\''
                + '}';
    }
}
