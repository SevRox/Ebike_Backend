package tech.getarays.Ebike.Backend.Boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepo boardRepo;

    @Autowired
    public BoardService(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    // REDO
    public List<Board> findBoardByUser(Long userId){
        return boardRepo.findBoardsByUser(userId);
    }
    public Board addBoard(Board board){
        return boardRepo.save(board);
    }

    public List<Board> findAllBoards() {
        return boardRepo.findAll();
    }

    public void deleteBoard(String macAddress){
        boardRepo.deleteByMac(macAddress);
    }
}
