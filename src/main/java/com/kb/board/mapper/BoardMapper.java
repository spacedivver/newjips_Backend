package com.kb.board.mapper;

import com.kb.board.dto.*;

import java.util.List;

public interface BoardMapper {

    List<Board> selectBoardList(BoardParam param);
    int selectBoardCount(BoardParam param);
    Board selectBoardByNno(long nno);
    int insertBoard(Board board);
    int updateBoard(Board board);
    int deleteBoard(long nno);

}
