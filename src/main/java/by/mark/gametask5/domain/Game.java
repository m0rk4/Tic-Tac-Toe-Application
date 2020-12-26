package by.mark.gametask5.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode(of = {"id"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("gameState")
    private String gameState;
    private String title;
    private String creator;
    private String opponent;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "game_tag",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonProperty("tags")
    private List<Tag> tags = new LinkedList<>();

    @JsonProperty("isPlaying")
    private boolean isPlaying;
    @JsonProperty("isCreatorChoice")
    private boolean isCreatorChoice;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @PrePersist
    void setCreationDate() {
        creationDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public boolean isCreatorChoice() {
        return isCreatorChoice;
    }

    public void setCreatorChoice(boolean creatorChoice) {
        isCreatorChoice = creatorChoice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
