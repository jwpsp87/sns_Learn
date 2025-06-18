package com.sns.post;

import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostBO postBO;

    /**
     * 글쓰기 API
     * @return
     */
    @PostMapping("/create")
    public Map<String, Object> create(
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("file") MultipartFile file,
            HttpSession session
    ) {

        Integer userId = (Integer)session.getAttribute("userId");
        String userLoginId = (String)session.getAttribute("userLoginId");

        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인을 해주세요!");
            return result;
        }

        PostEntity post = postBO.addPost(userId, userLoginId, content, file);

        if (post != null) {
            result.put("code", 200);
            result.put("result", "성공");
        } else{
            result.put("code", 500);
            result.put("error_message", "글 작성에 실패했습니다!");
        }

        return result;

    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(
            @RequestParam("postId") int postId,
            HttpSession session
    ) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인이 필요합니다.");
            return result;
        }

        postBO.deletePostByPostId(postId);

        result.put("code", 200);
        result.put("result", "성공");
        return result;


    }

}
