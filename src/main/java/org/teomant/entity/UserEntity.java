package org.teomant.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Users", schema = "public", catalog = "opencodetest")
public class UserEntity {

    @Id
    @SequenceGenerator( name = "users_sequence", sequenceName = "users_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "users_sequence" )
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @Basic
    @Column( name = "username", unique = true, nullable = false )
    private String username;

    @Basic
    @Column( name = "password", nullable = false )
    private String password;

    @OneToMany( fetch = FetchType.LAZY,
            mappedBy = "player",
            cascade = CascadeType.ALL ) private List<GameEntity> games;


}
