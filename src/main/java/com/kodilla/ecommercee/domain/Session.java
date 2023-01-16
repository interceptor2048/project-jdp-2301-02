package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SESSIONS")
public class Session {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name ="ID", unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "USER_KEY")
    private int userKey;

    @Column(name = "SESSION_ENDS")
    private long sessionEnds;

}


