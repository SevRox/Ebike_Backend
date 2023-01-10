package tech.getarays.backend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarays.backend.model.Board;
import tech.getarays.backend.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
@RestController // Tells Spring to render the resulting string directly back to the caller.
/* This is known as a stereotype annotation.
It provides hints for people reading the code and for Spring that the class plays a specific role.
In this case, our class is a web @Controller, so Spring considers it when handling incoming web requests.*/
@RequestMapping("/boards") @Slf4j
/* This Provides “routing” information.
It tells Spring that any HTTP request with the / path should be mapped to the home method.*/
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //annotation for mapping all the incoming HTTP request URLs to the corresponding controller methods
    @GetMapping("web/all")
    public ResponseEntity<List<Board>> getBoardByToken(@RequestHeader("Authorization") String token){
        String hash = token.substring("iWantedToUseCurseWordButItsAProcjetForSomeOneToRead ".length());
        Algorithm algorithm = Algorithm.HMAC256("changemeufokingidiot".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(hash);
        String username = decodedJWT.getSubject();
        log.info("Username got: {}",username);
        List<Board> board = boardService.findBoardByUser(username);
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
