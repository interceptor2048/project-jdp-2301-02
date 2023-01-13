package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "SESSIONS")
public class Session {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SESSION_ID", unique = true)
    private long sessionId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User userId;

    @NotNull
    @Column(name = "USER_KEY")
    private int userKey;

    @Column(name = "SESSION_ENDS")
    private long sessionEnds;

    public Session(long sessionId, int userKey, long sessionEnds) {
        this.sessionId = sessionId;
        this.userKey = userKey;
        this.sessionEnds = sessionEnds;
    }
}


