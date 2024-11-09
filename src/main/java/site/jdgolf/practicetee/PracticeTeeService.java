package site.jdgolf.practicetee;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PracticeTeeService {

    private final PracticeTeeRepository teeRepository;

    public PracticeTee findByTeeId(Integer teeId) {
        return teeRepository.findById(teeId);
    }

    public PracticeTee findAvailableTeeById(Integer teeId, LocalDate date, String time) {
        return teeRepository.findAvailableTeeById(teeId, date, time);
    }
    public List<PracticeTee> findAvailableTeesByDateTime(TeeType type, LocalDate date, String time) {
        return teeRepository.findNotReservedTeesByDateTime(type, date, time);
    }
}
