package by.mark.gametask5.controller;

import by.mark.gametask5.domain.Game;
import by.mark.gametask5.repo.GameRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Game create(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PutMapping("{id}")
    public Game update(
            @PathVariable("id") Game gameFromDb,
            @RequestBody Game game
    ) {
        // copy from game -> to gameFromDb ignoring id field
        BeanUtils.copyProperties(game, gameFromDb, "id");
        return gameRepo.save(gameFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Game game) {
        gameRepo.delete(game);
    }
}
