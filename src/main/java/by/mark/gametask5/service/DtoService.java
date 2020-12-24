package by.mark.gametask5.service;

import by.mark.gametask5.domain.Game;
import by.mark.gametask5.domain.Tag;
import by.mark.gametask5.dto.GameDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Configuration
public class DtoService {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Game convertToEntity(GameDto gameDto) {
        Game game = modelMapper.map(gameDto, Game.class);
        Set<Tag> tags = new HashSet<>();
        List<String> list = gameDto.getTags();
        for (String s : list) {
            Tag tag = new Tag();
            tag.setName(s);
            tags.add(tag);
        }
        game.setTags(tags);
        return game;
    }

    public GameDto convertToDto(Game game) {
        GameDto map = modelMapper.map(game, GameDto.class);
        List<String> tags = new LinkedList<>();
        game.getTags().forEach(t -> tags.add(t.getName()));
        map.setTags(tags);
        return map;
    }

}
