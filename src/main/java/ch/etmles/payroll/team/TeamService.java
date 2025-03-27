package ch.etmles.payroll.team;

import ch.etmles.payroll.exceptions.ResourceDeleteNotFound;
import ch.etmles.payroll.exceptions.ResourceIDNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    public static final String RESSOURCE_NAME = "team";

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    public Team getById(Long id){
        return teamRepository.findById(id).orElseThrow(() -> new ResourceIDNotFound(id, RESSOURCE_NAME));
    }

    public Team create(Team newTeam){
        return teamRepository.save(newTeam);
    }

    public Team update(Long id, Team updatedTeam){
        return teamRepository.findById(id).map(team -> {
                    team.setName(updatedTeam.getName());
                    team.setAddress(updatedTeam.getAddress());
                    team.setCity(updatedTeam.getCity());
                    team.setZipCode(updatedTeam.getZipCode());
                    team.setEmail(updatedTeam.getEmail());
                    return teamRepository.save(team);
        }).orElseGet(() -> { updatedTeam.setId(id);
            return teamRepository.save(updatedTeam);
        });
    }

    public void delete(Long id){
        if(teamRepository.existsById(id)){
            teamRepository.deleteById(id);
        } else {
            throw new ResourceDeleteNotFound(id, RESSOURCE_NAME);
        }
    }

}
