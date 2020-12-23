package by.mark.gametask5.controller;

import by.mark.gametask5.domain.Game;
import by.mark.gametask5.dto.GameDto;
import by.mark.gametask5.repo.GameRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepo gameRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public GameController(GameRepo gameRepo, ModelMapper modelMapper) {
        this.gameRepo = gameRepo;
        this.modelMapper = modelMapper;
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
    public Game create(@RequestBody GameDto gameDto, HttpSession session) {
        Game game = convertToEntity(gameDto);
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


    private Game convertToEntity(GameDto gameDto) {
        Game game = modelMapper.map(gameDto, Game.class);
               // TODO
        return game;
    }
}
