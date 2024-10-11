package com.kb.board.service;

import com.kb.board.dto.*;
import com.kb.board.mapper.BoardMapper;
import com.kb.common.pagination.PageInfo;
import com.kb.common.util.UploadFiles;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class BoardService {
    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}/board':'${file_save_location_other}/board'}")
    public String BASE_DIR;

    private final BoardMapper mapper;
    private static List<BoardCategory> categoryList;
    private static final int PAGE_LIMIT = 5;
    private static final int LIST_LIMIT = 12; // 게시글 보여질 list 수

    public BoardPageResult getBoardList(BoardParam boardParam) {
        int totalSize = mapper.selectBoardCount(boardParam);
        System.out.println(boardParam.getAmount());
        int listLimit = boardParam.getAmount() == 0 ? LIST_LIMIT : boardParam.getAmount();
        PageInfo pageInfo = new PageInfo(boardParam.getPage(), totalSize, listLimit, PAGE_LIMIT);
//        boardParam.setLimit(pageInfo.getListLimit());
        boardParam.setLimit(6);
        boardParam.setOffset(pageInfo.getStartList() - 1);


        List<Board> boardList = mapper.selectBoardList(boardParam);
        System.out.println(boardList);
        if (boardList == null || boardList.isEmpty()) {
            boardList = new ArrayList<>();
        }
        return new BoardPageResult(boardList, boardParam, pageInfo, totalSize);
    }

    @Transactional
    public Board getBoard(long nno) {
        Board board = mapper.selectBoardByNno(nno);
        board.setReadCount(board.getReadCount() + 1);

        log.info("========================" + board);
        return Optional.of(board)
                .orElseThrow(NoSuchElementException::new);
    }

}
