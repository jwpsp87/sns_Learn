package com.sns.comment.mapper;


import com.sns.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment (
            @Param("postId") int postId,
            @Param("userId") int userId,
            @Param("content") String content);

    public List<Comment> selectCommentList();

    public List<Comment> selectCommentListByPostId(int postId);

    public void deleteCommentById(int commentId);

    public void deleteCommentListByPostId(int postId);
}


