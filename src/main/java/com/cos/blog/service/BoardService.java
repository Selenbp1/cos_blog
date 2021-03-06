package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC를 해줌
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final  ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user){   //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return  boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글상세보기 실패!!");
                });
    }

    @Transactional
    public void 글삭제하기(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정(int id, Board requestBoard){
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
            return new IllegalArgumentException("글찾기 실패!!");
        }); //영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수로 종료시에 트랜잭션이 Service가 종료될 때, 트랜잰셕이 종료되고, 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto){
        int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    @Transactional
    public void 댓글삭제(int replyId){
        replyRepository.deleteById(replyId);
    }
}
