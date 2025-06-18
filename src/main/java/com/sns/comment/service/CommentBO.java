package com.sns.comment.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentBO {

    private final CommentMapper commentMapper;
    private final UserBO userBO;

    public int addComment(int postId, int userId, String content) {

        return commentMapper.insertComment(postId, userId, content);
    }

    public List<Comment> getCommentList() {

        return commentMapper.selectCommentList();
    }

     // i:글번호(postId)
     // o: List<CommentDTO>
    public List<CommentDTO> generateCommentListByPostId(int postId) {
        List<CommentDTO> commentDTOList = new ArrayList<>();

         // 글에 해당하는 댓글 리스트 가져옴
        List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);

         // 반복문: Comment -> CommentDTO => commentList에 넣기
         //                 댓글쓴이도 집어넣기
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();

             //  댓글 1개
            commentDTO.setComment(comment);

             // 댓글쓴이 1개
            commentDTO.setUser(userBO.getUserEntityById(comment.getUserId()));

             // 반드시 리스트에 넣기!!!
            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }

    public void deleteCommentById(int commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    public void deleteCommentsByPostId(int postId) {
        commentMapper.deleteCommentListByPostId(postId);
    }
}
