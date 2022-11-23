package tech.getarays.Ebike.Backend.Boards;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Tells Spring to render the resulting string directly back to the caller.
/* This is known as a stereotype annotation.
It provides hints for people reading the code and for Spring that the class plays a specific role.
In this case, our class is a web @Controller, so Spring considers it when handling incoming web requests.*/
@RequestMapping("/boards")
/* This Provides “routing” information.
It tells Spring that any HTTP request with the / path should be mapped to the home method.*/
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //annotation for mapping all the incoming HTTP request URLs to the corresponding controller methods
    @GetMapping("/web/all/{user_id}")
    public ResponseEntity<List<Board>> getBoardByUser(@PathVariable("user_id") Long userId){
        List<Board> board = boardService.findBoardByUser(userId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/web/register")
    public ResponseEntity<Board> addBoard(@RequestBody Board board){
        Board newBoard = boardService.addBoard(board);
        return new ResponseEntity<>(newBoard, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.findAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @DeleteMapping("/web/delete/{mac_address}")
    public ResponseEntity<?> deleteBoard(@PathVariable("mac_address") String mac_address){
        boardService.deleteBoard(mac_address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
