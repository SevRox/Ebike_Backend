package tech.getarays.Ebike.Backend.Time;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
@RestController
@RequestMapping("/time")  // jeżeli to nie jest wymagane to jest request bedzie opsługiwany w obu kontrolerach
public class TimeController {

    private LocalDateTime recordStart;
    private String currentBoard;
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public void addTime(@RequestBody Time time){
        Time newTime = timeService.addTime(time);
    }

    @PostMapping("/web/recordstatus/{status}")
    public ResponseEntity<Time> setStartEnd(@PathVariable("status") boolean stat){
        LocalDateTime currentTime = LocalDateTime.now();
        if(stat){
            setStart(currentTime);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            // gets first data above currentTime then get mac address
            setCurrentBoard(timeService.getMacByTime(getRecordStart()));
            Time newTime = new Time( getCurrentBoard().toCharArray(), getRecordStart(), currentTime);
            addTime(newTime);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/web/{board_mac}/timestamps")
    public ResponseEntity<List<Time>> getALL(@PathVariable("board_mac") String boardMac){
        List<Time> time = timeService.getAll(boardMac);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    public void setStart(LocalDateTime start) {
        recordStart = start;
    }

    public LocalDateTime getRecordStart() {
        return recordStart;
    }

    public String getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(String currentBoard) {
        this.currentBoard = currentBoard;
    }
}