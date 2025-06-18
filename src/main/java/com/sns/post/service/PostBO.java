package com.sns.post.service;
import com.sns.comment.service.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.service.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class PostBO {

    private final PostRepository postRepository;
    private final FileManagerService fileManager;
    private final CommentBO commentBO;
    private final LikeBO likeBO;

    public List<PostEntity> getPostList() {

        return postRepository.findAllByOrderByIdDesc();
    }

     // input: 파라미터들
     // ouput: PostEntity
    public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {

         // 업로드 후 imagePath를 받아옴
        String imagePath = fileManager.uploadFile(file, userLoginId);
        if (imagePath == null) {
            return null;
        }

        return postRepository.save(
                PostEntity.builder()
                          .userId(userId)
                          .content(content)
                          .imagePath(imagePath)
                          .build());
    }

     // 글 삭제
     // i: postId
     // o: X
    @Transactional  // DB 수행시 하나라도 실패하면 자동 복구 isolation(격리 수준)
    public void deletePostByPostId(int postId) {
       // 기존글 가져오기 => 이미지 path
      PostEntity post = postRepository.findById(postId).orElse(null);
      if (post == null) {
          log.error("[글 삭제] post is null. postId:{}", postId);
          return;
      }

       // 글 삭제(트랜잭션)
      postRepository.delete(post);      // 엔티티를 넘기면 @Id 값으로 Where조건

       // 이미지 삭제
      fileManager.deleteFile(post.getImagePath());

       // 댓글 삭제
      commentBO.deleteCommentsByPostId(postId);

       // 좋아요 삭제
      likeBO.deleteLikesByPostId(postId);

    }


}
