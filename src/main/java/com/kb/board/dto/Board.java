package com.kb.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Board {

    private long nno;
    private String title;
    private String content;
    private Date createdAt;
    private String lan;
    private String thumb_img;
    private int readCount;

    private ArrayList<BoardReply> replyList;
    private ArrayList<BoardAttachFile> boardAttachFileList;
}

