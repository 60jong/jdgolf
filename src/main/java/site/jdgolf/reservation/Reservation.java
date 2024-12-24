package site.jdgolf.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.jdgolf.member.Member;
import site.jdgolf.practicetee.PracticeTee;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member reservingMember;
    private Integer headCount;

    @OneToOne(fetch = FetchType.EAGER)
    private PracticeTee practiceTee;

    private LocalDate reserveDate;
    private String reserveTime;
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    private Boolean accept = false;
    private LocalDateTime requestTimestamp;
    private LocalDateTime acceptTimestamp;

    public Reservation(Member member, ReservationType type,
        LocalDate reserveDate, String reserveTime, Integer headCount) {
        this.reservingMember = member;
        this.reservationType = type;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
        this.headCount = headCount;
    }

    public void acceptedTo(PracticeTee tee) {
        this.accept = true;
        this.practiceTee = tee;
    }

    public void rejected() {
        this.accept = false;
        this.practiceTee = null; // Implicitly set null
    }
}
