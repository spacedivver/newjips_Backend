package com.kb.buddiz.service;

import com.kb.board.dto.Board;
import com.kb.board.dto.BoardAttachFile;
import com.kb.board.dto.BoardDTO;
import com.kb.buddiz.dto.*;
import com.kb.buddiz.mapper.BuddizMapper;
import com.kb.common.pagination.PageInfo;
import com.kb.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class BuddizService {
    public String BASE_DIR;

    private final BuddizMapper mapper;
    private static final int PAGE_LIMIT = 12;
    private static final int LIST_LIMIT = 10;

    public BuddizPageResult getBuddizList(BuddizParam buddizParam) {
        int totalSize = mapper.selectBuddizCount(buddizParam);
//        System.out.println(buddizParam);
//        System.out.println(totalSize);
        PageInfo pageInfo = new PageInfo(buddizParam.getPage(), totalSize, LIST_LIMIT, PAGE_LIMIT);
        buddizParam.setLimit(pageInfo.getListLimit());
        buddizParam.setOffset(pageInfo.getStartList() - 1);
        List<Buddiz> buddizList = mapper.selectBuddizList(buddizParam);
        if (buddizList == null || buddizList.isEmpty()) {
            buddizList = new ArrayList<>();
        }
        return new BuddizPageResult(buddizList, buddizParam, pageInfo,totalSize);
    }

    public ReviewList getReviewList(long uno) {
        int totalSize = mapper.selectReviewCount(uno);
        List<Review> reviewList = mapper.selectReviewByUno(uno);
        double avg=mapper.selectReviewAvg(uno);
//        String profilePic=mapper.
        if (reviewList == null || reviewList.isEmpty()) {
            reviewList = new ArrayList<>();
        }
        return new ReviewList(reviewList,totalSize, avg);
    }

    @Transactional
    public Buddiz getBuddiz(long uno) {
        System.out.println("get Buddiz "+uno);
        Buddiz buddiz = mapper.selectBuddizByUno(uno);
        System.out.println("get Buddiz "+buddiz);
        return Optional.of(buddiz)
                .orElseThrow(NoSuchElementException::new);
    }

//    @Transactional
//    public Buddiz getReview(long uno) {
//        log.info("get......" + uno);
//        Buddiz review = mapper.selectReviewByUno(uno);
//        log.info("========================" + review);
//        return Optional.of(review)
//                .orElseThrow(NoSuchElementException::new);
//    }

    @Transactional(rollbackFor = Exception.class) // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
    public Buddiz createBuddiz(Buddiz buddiz) {
        int result = mapper.insertBuddiz(buddiz);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return getBuddiz(buddiz.getUno());
    }

    @Transactional(rollbackFor = Exception.class) // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
    public Buddiz createWish(Buddiz buddiz) {
        int result = mapper.insertWish(buddiz);
        System.out.println("service"+buddiz.getUno());
        System.out.println("service"+buddiz.getWished_id());
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return getBuddiz(buddiz.getWished_id()); // 수정된 부분
    }


    @Transactional
    public Buddiz updateBuddiz(Buddiz buddiz) {
//        Board oldBoard = getBoard(board.getBno());

        int result = mapper.updateBuddiz(buddiz);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return getBuddiz(buddiz.getUno());
    }

    @Transactional
    public Buddiz deleteBuddiz(long uno) {
        Buddiz buddiz = getBuddiz(uno);

        int result = mapper.deleteBuddiz(uno);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return buddiz;
    }

    @Transactional
    public boolean checkUnPicked(Buddiz buddiz) {
        int result = mapper.checkPicked(buddiz);
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }
}
