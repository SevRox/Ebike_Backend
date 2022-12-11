package tech.getarays.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarays.backend.model.Time;
import tech.getarays.backend.repository.TimeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public Time addTime(Time time){
        return timeRepository.save(time);
    }

    public List<Time> getAll(String boardMac){
        return timeRepository.getAllByMac(boardMac);
    }

    public String getMacByTime(LocalDateTime time){
        return timeRepository.getMacByTime(time);
    }

    public void deleteById(Long Id){
        timeRepository.deleteById(Id);
    }
}
