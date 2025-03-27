package ch.etmles.payroll.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

     /* curl -i localhost:8080/teams */
    @GetMapping()
    List<Team> all(){
        return teamService.getAll();
    }

    /* curl -i localhost:8080/teams/1 */
    @GetMapping("/{id}")
    Team one(@PathVariable Long id){
        return teamService.getById(id);
    }

    /*  curl -i -X POST localhost:8080/teams ^
    -H "Content-type:application/json" ^
    -d "{\"name\": \"AC MILAN\", \"address\": \"Piazzale Angelo Moratti\", \"city\": \"Milano\", \"zipCode\": \"20151\", \"email\": \"contact@acmilan.com\"}"
    */
    @PostMapping()
    Team add(@RequestBody Team newTeam){
        return teamService.create(newTeam);
    }

    /* curl -i -X PUT localhost:8080/teams/1 ^
    -H "Content-type:application/json" ^
    -d "{\"name\": \"Inter Milan\", \"address\": \"Piazzale Angelo Moratti\", \"city\": \"Milano\", \"zipCode\": \"20151\", \"email\": \" contact@inter.it\"}"
    */
    @PutMapping("/{id}")
    Team replace(@RequestBody Team newTeam, @PathVariable Long id) {
        return teamService.update(id, newTeam);
    }

    /* curl -i -X DELETE localhost:8080/teams/2 */
    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) {
        teamService.delete(id);
    }


}
