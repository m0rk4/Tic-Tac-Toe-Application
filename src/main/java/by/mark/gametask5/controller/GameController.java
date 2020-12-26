package by.mark.gametask5.controller;

import by.mark.gametask5.domain.Game;
import by.mark.gametask5.domain.Message;
import by.mark.gametask5.repo.GameRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepo gameRepo;


    @Autowired
    public GameController(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    @GetMapping
    public List<Game> list() {
        return gameRepo.findAll();
    }

    @GetMapping("{id}")
    public Game getOne(@PathVariable("id") Game game) {
        return game;
    }

    @PostMapping
    public Game create(@RequestBody Game game, HttpSession session) {
        game.setCreator(session.getId());
        return gameRepo.save(game);
    }


    @PutMapping("{id}")
    public Game update(
            @PathVariable("id") Game gameFromDb,
            @RequestBody Game game,
            HttpSession session
    ) {
        // copy from game -> to gameFromDb ignoring id field
        BeanUtils.copyProperties(game, gameFromDb, "id");
        gameFromDb.setOpponent(session.getId());
        return gameRepo.save(gameFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Game game) {
        gameRepo.delete(game);
    }

    @MessageMapping("/changeGame/{id}")
    @SendTo("/topic/gamestates/{id}")
    public Game change(Game game) {
        return gameRepo.save(game);
    }

    @MessageMapping("/showAlert/{id}")
    @SendTo("/topic/alert/{id}")
    public Message showAlert(Message message) {
        gameRepo.deleteById(message.getId());
        return message;
    }
}
