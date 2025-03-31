package ch.etmles.payroll.memberRole;

import ch.etmles.payroll.member.Member;
import ch.etmles.payroll.member.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberRoles")
public class MemberRoleController {

    private final MemberRoleService memberRoleService;

    public MemberRoleController(MemberRoleService memberRoleService) {
        this.memberRoleService = memberRoleService;
    }

    /* curl sample :
    curl -i localhost:8080/memberRoles
    */
    @GetMapping()
    List<MemberRole> all(){
        return memberRoleService.getAll();
    }

    /* curl sample :
    curl -i localhost:8080/memberRoles/1
    */
    @GetMapping("/{id}")
    MemberRole one(@PathVariable Long id){
        return memberRoleService.getById(id);
    }

    /* curl sample :
    curl -i -X POST localhost:8080/memberRoles ^
    -H "Content-type:application/json" ^
    -d "{\"role\": \"Captain\", \"memberId\": 1, \"teamId\": 1}"
    */
    @PostMapping()
    MemberRole add(@RequestBody MemberRoleDTO memberRole){
        return memberRoleService.create(memberRole);
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/memberRoles/1 ^
    -H "Content-type:application/json" ^
    -d "{\"role\": \"Captain\", \"member\": 1, \"team\": 1}"
    */
    @PutMapping("/{id}")
    MemberRole replace(@RequestBody MemberRoleDTO memberRole, @PathVariable Long id) { return memberRoleService.update(id, memberRole); }

    /* curl sample :
    curl -i -X DELETE localhost:8080/memberRoles/1
    */
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) {
        memberRoleService.delete(id);
    }

}
