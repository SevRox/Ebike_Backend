package tech.getarays.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarays.backend.model.Board;
import tech.getarays.backend.repository.BoardRepository;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // REDO
    public List<Board> findBoardByUser(Long userId){
        return boardRepository.findBoardsByUser(userId);
    }
    public Board addBoard(Board board){
        return boardRepository.save(board);
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public void deleteBoard(String macAddress){
        boardRepository.deleteByMac(macAddress);
    }
}
