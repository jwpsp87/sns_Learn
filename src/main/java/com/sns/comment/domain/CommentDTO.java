package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

// 댓글 하나에 대응
@ToString
@Data
public class CommentDTO {

     // 댓글 1개
    private Comment comment;

     // 댓글쓴이 1개
    private UserEntity user;


}
