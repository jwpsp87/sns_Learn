package com.sns.timeline.service;

import com.sns.comment.domain.CommentDTO;
import com.sns.comment.service.CommentBO;
import com.sns.like.service.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineBO {

    private final PostBO postBO;
    private final UserBO userBO;
    private final CommentBO commentBO;
    private final LikeBO likeBO;

     // i: userId(비로그인null 또는 로그인int)
     // o: List<CardDTO>
    public List<CardDTO> generateCardList(Integer userId) {

        List<CardDTO> cardList = new ArrayList<>();

         // 글 목록을 가져온다. List<PostEntity>
        List<PostEntity> postList = postBO.getPostList();

         // 반복문 돌린다. PostEntity -> CardDTO => 리스트에 넣는다.
        for (PostEntity postEntity : postList) {
             // 하나의 카드 = 글 1개와 매핑
            CardDTO card = new CardDTO();
             // 글
            card.setPost(postEntity);
             // 글쓴이
            int postAuthorId = postEntity.getUserId();
            UserEntity user = userBO.getUserEntityById(postEntity.getUserId());
            card.setUser(user);

             // 댓글 N개
            List<CommentDTO> commentList = commentBO.generateCommentListByPostId(postEntity.getId());
            card.setCommentList(commentList);

             // TODO: 좋아요 개수 채우기
            int likeCount = likeBO.getLikeCountByPostId(postEntity.getId());
            card.setLikeCount(likeCount);

             // TODO: 좋아요 눌렀는지 여부
            boolean filledLike = likeBO.isFilledLike(userId, postEntity.getId());
            card.setFilledLike(filledLike);

             // !!! 마지막에 리스트에 꼭 담기 !!!
            cardList.add(card);
        }

        return cardList;

    }


}
