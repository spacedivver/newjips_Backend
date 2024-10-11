package com.kb.board.controller;

import com.kb.board.dto.*;
import com.kb.board.service.BoardService;
import com.kb.member.dto.Member;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
@Api(value = "BoardController", tags = "게시판 정보")
@PropertySource({"classpath:/application.properties"})
public class BoardController {

    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}/board':'${file_save_location_other}/board'}")
    public String BASE_DIR;

    private final BoardService service;

    @GetMapping("")
    public ResponseEntity<BoardPageResult> getList(BoardParam boardParam) {
//        System.out.println(boardParam.getAmount());
        System.out.println(boardParam);
        BoardPageResult result = service.getBoardList(boardParam);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail/{nno}")
    public ResponseEntity<Board> getById(@PathVariable long nno) {
        return ResponseEntity.ok(service.getBoard(nno));
    }

}
