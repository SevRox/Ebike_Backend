package tech.getarays.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarays.backend.dto.MacBoardDto;
import tech.getarays.backend.model.Data;
import tech.getarays.backend.service.DataService;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
@RestController
@RequestMapping("/data")
public class DataController {

    private boolean flag; // record status // TODO *****
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
        setFlag(false);
    }

    // gets only one row with the lowest ID (live data)
    // TODO *****
    @GetMapping("/web/{board_mac}/livedata")
    public ResponseEntity<Data> getNewest(@PathVariable("board_mac") String boardMac) {
        Data data = dataService.findNewest(boardMac);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // TODO *****
    @GetMapping("/web/{board_mac}/record/{id}")
    public ResponseEntity<List<Data>> getInterval(@PathVariable("board_mac") String boardMac, @PathVariable("id") Long timeId){
        List<Data> intervalData = dataService.dataFromTimeStamps(timeId, boardMac);
        return new ResponseEntity<>(intervalData, HttpStatus.OK);
    }

    // TODO *****
    @PostMapping("/web/recordstatus/{status}")
    public void setCurrentFlag(@PathVariable("status") boolean status) {
        setFlag(status);
        // TODO return
    }

    // TODO *****
    @PostMapping("/board/{board_mac}/livedata")
    public ResponseEntity<Data> postData(@RequestBody Data data){
        Data newData;
        data.setTime_stamp(LocalDateTime.now()); // board is unable to add current time to the send data. It is handled by this
        if(getFlag()){
            newData = dataService.addData(data);
        }
        else{
            newData = dataService.updateData(data);
        }
        return new ResponseEntity<>(newData, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/web/lastboard", produces = "application/json")
    public ResponseEntity<MacBoardDto> lastSelectedBoard(){
        MacBoardDto boardMac = dataService.lastSelectedBoard();
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
