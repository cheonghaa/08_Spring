package org.scoula.board.service;


import java.util.*;

import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service //bean으로 등록을 위한 어노테이션
@RequiredArgsConstructor //final 멤버를 인자로 가지는 생성자 추가
public class BoardServiceImpl implements BoardService
{
    final private BoardMapper mapper; //생성자가 1개인 경우 생성자 주입으로 초기화



    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");
        return mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // BoardDTO의 스트림
                .toList(); // List<BoardDTO> 변환
    }
    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);
        BoardDTO board = BoardDTO.of(mapper.get(no)); //DTO객체로 하나만 변환
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void create(BoardDTO board) {
        log.info("create......" + board);
        mapper.create(board.toVo());
    }
    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return mapper.update(board.toVo()) == 1;
    }


    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return mapper.delete(no) == 1;
    }
}
