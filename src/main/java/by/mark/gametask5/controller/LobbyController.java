package by.mark.gametask5.controller;

import by.mark.gametask5.domain.Game;
import by.mark.gametask5.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lobby")
public class LobbyController {

    private final GameRepo gameRepo;

    @Autowired
    public LobbyController(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    @GetMapping("{id}")
    public String currentLobby(HttpSession session, @PathVariable("id") Game gameFromDb) {
        String currSession = session.getId();
        if (currSession.equals(gameFromDb.getCreator()) || currSession.equals(gameFromDb.getOpponent()))
            return "game";
        return "redirect:/";

    }
}
