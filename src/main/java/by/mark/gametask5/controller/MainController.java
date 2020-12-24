package by.mark.gametask5.controller;

import by.mark.gametask5.dto.GameDto;
import by.mark.gametask5.service.DtoService;
import by.mark.gametask5.domain.Tag;
import by.mark.gametask5.repo.GameRepo;
import by.mark.gametask5.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    private final TagRepo tagRepo;
    private final GameRepo gameRepo;
    private final DtoService dtoService;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(TagRepo tagRepo, GameRepo gameRepo, DtoService dtoService) {
        this.tagRepo = tagRepo;
        this.gameRepo = gameRepo;
        this.dtoService = dtoService;
    }

    @GetMapping
    public String main(Model model) {
        Map<Object, Object> frontData = new HashMap<>();
        frontData.put("tags", tagRepo.findAll().stream()
                .map(Tag::getName)
                .collect(Collectors.toList())
        );

        Iterable<GameDto> gameDtos = gameRepo.findAll().stream()
                .map(dtoService::convertToDto)
                .collect(Collectors.toList());
        frontData.put("games", gameDtos);

        model.addAttribute("frontData", frontData);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

}
