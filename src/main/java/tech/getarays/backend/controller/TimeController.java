package tech.getarays.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarays.backend.model.Time;
import tech.getarays.backend.service.TimeService;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
@RestController
@RequestMapping("/time")
public class TimeController {

    private LocalDateTime recordStart;
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public void addTime(@RequestBody Time time) {
        Time newTime = timeService.addTime(time);
    }

    // TODO *****
    @PostMapping("/web/{mac}/recordstatus/{status}")
    public ResponseEntity<Time> setStartEnd(@PathVariable("status") boolean stat, @PathVariable("mac") String boardMac) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (stat) {
            setStart(currentTime);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            Time newTime = new Time(boardMac.toCharArray(), getRecordStart(), currentTime);
            addTime(newTime);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // TODO *****
    @GetMapping("/web/{board_mac}/timestamps")
    public ResponseEntity<List<Time>> getALL(@PathVariable("board_mac") String boardMac){
        List<Time> time = timeService.getAll(boardMac);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    // TODO *****
    @DeleteMapping("web/delete/{id}")
    public ResponseEntity<?> deleteTime(@PathVariable("id") Long Id){
        timeService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void setStart(LocalDateTime start) {
        recordStart = start;
    }

    public LocalDateTime getRecordStart() {
        return recordStart;
    }
}