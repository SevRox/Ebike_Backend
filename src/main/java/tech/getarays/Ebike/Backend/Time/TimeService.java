package tech.getarays.Ebike.Backend.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeService {

    private final TimeRepo timeRepo;

    @Autowired
    public TimeService(TimeRepo timeRepo) {
        this.timeRepo = timeRepo;
    }

    public Time addTime(Time time){
        return timeRepo.save(time);
    }

    public List<Time> getAll(String boardMac){
        return timeRepo.getAllByMac(boardMac);
    }

    public String getMacByTime(LocalDateTime time){
        return timeRepo.getMacByTime(time);
    }

    public void deleteById(Long Id){
        timeRepo.deleteById(Id);
    }
}
