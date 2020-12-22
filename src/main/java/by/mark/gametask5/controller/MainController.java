package by.mark.gametask5.controller;

import by.mark.gametask5.repo.GameRepo;
import by.mark.gametask5.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final TagRepo tagRepo;
    private final GameRepo gameRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(TagRepo tagRepo, GameRepo gameRepo) {
        this.tagRepo = tagRepo;
        this.gameRepo = gameRepo;
    }


    @GetMapping
    public String main(Model model) {
        model.addAttribute("gameList", gameRepo.findAll());
        model.addAttribute("tagList", tagRepo.findAll());
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

}
