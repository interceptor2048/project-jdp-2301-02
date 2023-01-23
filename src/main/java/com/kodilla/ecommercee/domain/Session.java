package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "USER_KEY")
    private int userKey;
    @Column(name = "SESSION_START", insertable = false, updatable = false)
    private LocalDateTime sessionStart;

    @Column(name = "SESSION_END", insertable = false, updatable = false )
    private LocalDateTime sessionEnds;


    public Session(User user, int userKey, LocalDateTime sessionStart, LocalDateTime sessionEnds) {
        this.user = user;
        this.userKey = userKey;
        this.sessionStart = sessionStart;
        this.sessionEnds = sessionEnds;
    }
}
