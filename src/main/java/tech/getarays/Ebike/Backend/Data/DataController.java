package tech.getarays.Ebike.Backend.Data;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
@RestController
@RequestMapping("/data")
public class DataController {

    private boolean flag; // record status
    private final DataService dataService;

    //@Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
        setFlag(false);
    }

    // gets only one row with the lowest ID (live data)
    @GetMapping("/web/{board_mac}/livedata")
    public ResponseEntity<Data> getNewest(@PathVariable("board_mac") String boardMac) {
        Data data = dataService.findNewest(boardMac);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/web/{board_mac}/record/{id}")
    public ResponseEntity<List<Data>> getInterval(@PathVariable("board_mac") String boardMac, @PathVariable("id") Long timeId){
        List<Data> intervalData = dataService.dataFromTimeStamps(timeId, boardMac);
        return new ResponseEntity<>(intervalData, HttpStatus.OK);
    }

    @PostMapping("/web/recordstatus/{status}")
    public void setCurrentFlag(@PathVariable("status") boolean status) {
        setFlag(status);
        // TODO return
    }

    @PostMapping("/board/{board_mac}/livedata")
    public ResponseEntity<Data> postData(@RequestBody Data data){
        Data newData;
        if(getFlag()){
            // board is unable to add current time to the send data. It is handled by this
            data.setTime_stamp(LocalDateTime.now());
            newData = dataService.addData(data);
        }
        else{
            data.setTime_stamp(LocalDateTime.now());
            newData = dataService.updateData(data);
        }
        return new ResponseEntity<>(newData, HttpStatus.ACCEPTED);
    }

    @GetMapping("/web/lastboard")
    public ResponseEntity<String> lastSelectedBoard(){
        String boardMac = dataService.lastSelectedBoard();
        return new ResponseEntity<>(boardMac, HttpStatus.OK);
    }

    //getters and setters
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
