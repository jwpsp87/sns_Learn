package com.sns.timeline.domain;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

// 단건: 글1개=카드1개
@ToString
@Data
public class CardDTO {

     // 글 1개
    private PostEntity post;

     // 글쓴이 정보
    private UserEntity user;

    // 댓글 N개
    private List<CommentDTO> commentList;

     // 좋아요 수 N개
    private int likeCount;

     // 내가 좋아요를 눌렀는지 여부
    private boolean filledLike;     // 눌리면 true

}
