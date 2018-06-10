package org.teomant.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Attempts", schema = "public", catalog = "opencodetest")
public class AttemptEntity {
    @Id
    @SequenceGenerator( name = "attempts_sequence", sequenceName = "attempts_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "attempts_sequence" )
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;


    @ManyToOne( optional = false )
    @JoinColumn( name = "game" )
    private GameEntity game;

    @Basic
    @Column( name = "value")
    private String value;

    @Basic
    @Column( name = "correct" )
    private boolean correct;

    @Basic
    @Column( name = "bull" )
    private Long bull;

    @Basic
    @Column( name = "cow" )
    private Long cow;
}
