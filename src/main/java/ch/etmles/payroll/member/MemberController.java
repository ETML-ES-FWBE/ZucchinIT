package ch.etmles.payroll.member;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* curl sample :
    curl -i localhost:8080/members
    */
    @GetMapping()
    List<Member> all(){
        return memberService.getAll();
    }

    /* curl sample :
    curl -i localhost:8080/members/1
    */
    @GetMapping("/{id}")
    Member one(@PathVariable Long id){
        return memberService.getById(id);
    }

    /* curl sample :
    curl -i -X POST localhost:8080/members ^
        -H "Content-type:application/json" ^
        -d "{\"firstName\": \"Mike\", \"lastName\": \"Maignan\", \"email\": \"mike.maignan@ac-milan.it\", \"type\": \"GOALKEEPER\", \"flocking\": 18}"
    */
    @PostMapping()
    Member add(@RequestBody Member member){
        return memberService.create(member);
    }

    /* curl sample :
    curl -i -X PATCH localhost:8080/members/1 ^
        -H "Content-type:application/json" ^
        -d "{\"firstName\": \"Mikey\", \"type\": \"DEFENDER\"}"
    */
    @PatchMapping("/{id}")
    Member modify(@RequestBody Map<String, Object> editedEmployee, @PathVariable Long id) {
        return memberService.patch(id, editedEmployee);
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/members/1 ^
        -H "Content-type:application/json" ^
        -d "{\"firstName\": \"Mario\", \"lastName\": \"Balotelli\", \"email\": \"mario.balotelli@genova-cfc.it\", \"type\": \"ATTACKER\", \"flocking\": 45}"
    */
    @PutMapping("/{id}")
    Member replace(@RequestBody Member newEmployee, @PathVariable Long id) {
        return memberService.update(id, newEmployee);
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/members/1
    */
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) {
        memberService.delete(id);
    }
}
