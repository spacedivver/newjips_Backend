package com.kb.blame.service;

import com.kb.blame.dto.Blame;
import com.kb.blame.dto.BlameBuddiz;
import com.kb.blame.dto.BlameEstate;
import com.kb.blame.mapper.BlameMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@RequiredArgsConstructor
public class BlameService {
    private final BlameMapper mapper;

    @Transactional
    public Blame insertBlame(Blame blame) {
        log.info("Blame : Insert Blame...............");
        int result = mapper.insertBlame(blame);
        if (result != 1) {
            throw new NoSuchElementException();
        }
        return selectBlameById(blame.getBlamedId());
    }

    @Transactional
    public Blame selectBlameById(long blameId) {
        log.info("Blame : Select Blame...............");
        return mapper.selectBlameById(blameId);
    }

    @Transactional
    public List<BlameBuddiz> selectBuddizBlameList(long uno) {
        log.info("Blame : Select Buddiz Blame...............");
        return mapper.selectBuddizBlameList(uno);
    }

    @Transactional
    public List<BlameEstate> selectEstateBlameList(long uno) {
        log.info("Blame : Select Buddiz Blame...............");
        return mapper.selectEstateBlameList(uno);
    }
}