package com.sns.comment;

import com.sns.comment.service.CommentBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentRestController {

    private final CommentBO commentBO;

    /**
     * 댓글 쓰기 API
     * @param postId
     * @param content
     * @param session
     * @return
     */

     // http://localhost:8080/comment/create?postId=2&content=댓글달기!~!!
     //@GetMapping("/create")
    @PostMapping("/create")
    public Map<String, Object> create(
            @RequestParam("postId") int postId,
            @RequestParam("content") String content,
            HttpSession session
    ) {
         // 로그인 여부
        Integer userId = (Integer)session.getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인을 해주세요");
            return result;
        }

         // insert DB
        int rowCount = commentBO.addComment(postId, userId, content);

         // 응답값
        if (rowCount > 0) {
            result.put("code", 200);
            result.put("result", "성공");
        } else{
            result.put("code", 500);
            result.put("error_message", "댓글 쓰기에 실패했습니다.");
        }

        return result;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(
            @RequestParam("commentId") int commentId,
            HttpSession session
    ) {
        // 로그인 여부 확인
       Map<String, Object> result = new HashMap<>();
       Integer userId = (Integer)session.getAttribute("userId");
       if (userId == null) {
           result.put("code", 403);
           result.put("error_message", "로그인이 되지 않은 사용자입니다.");
           return result;
       }

        // 삭제
        commentBO.deleteCommentById(commentId);

        // 응답값
       result.put("code", 200);
       result.put("result", "성공");
       return result;


    }

}
