package by.mark.gametask5.controller;

import by.mark.gametask5.service.DtoService;
import by.mark.gametask5.domain.Game;
import by.mark.gametask5.dto.GameDto;
import by.mark.gametask5.repo.GameRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameRepo gameRepo;
    private final DtoService dtoService;

    @Autowired
    public GameController(GameRepo gameRepo,  DtoService dtoService) {
        this.gameRepo = gameRepo;
        this.dtoService = dtoService;
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
    public GameDto create(@RequestBody GameDto gameDto, HttpSession session) {
        Game game = dtoService.convertToEntity(gameDto);
        gameRepo.save(game);
        return dtoService.convertToDto(game);
    }


    @PutMapping("{id}")
    public GameDto update(
            @PathVariable("id") Game gameFromDb,
            @RequestBody GameDto game,
            HttpSession session
    ) {
        // copy from game -> to gameFromDb ignoring id field
        BeanUtils.copyProperties(game, gameFromDb, "id");
        gameFromDb.setOpponent(session.getId());
        gameRepo.save(gameFromDb);
        return dtoService.convertToDto(gameFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Game game) {
        gameRepo.delete(game);
    }

}
