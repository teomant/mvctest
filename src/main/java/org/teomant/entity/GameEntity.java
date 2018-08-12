package org.teomant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "player")
@Entity
@Table(name = "Games", schema = "public", catalog = "opencodetest")
public class GameEntity {

    @Id
    @SequenceGenerator( name = "games_sequence", sequenceName = "games_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "games_sequence" )
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne( optional = false )
    @JoinColumn( name = "player" )
    private UserEntity player;

    @Basic
    @Column( name = "task" )
    private String task;

    @Basic
    @Column( name = "finished" )
    private boolean finished;

    @OneToMany( fetch = FetchType.EAGER,
            mappedBy = "game",
            cascade = CascadeType.ALL ) private List<AttemptEntity> attempts;
}
