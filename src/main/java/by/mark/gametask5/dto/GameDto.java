package by.mark.gametask5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("isPlaying")
    private boolean isPlaying;
    @JsonProperty("tags")
    private List<String> tags;
}
