package site.jdgolf.practicetee;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PracticeTee {
    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TeeType teeType;
}
