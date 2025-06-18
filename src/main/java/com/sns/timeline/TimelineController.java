package com.sns.timeline;

import com.sns.comment.domain.Comment;
import com.sns.comment.service.CommentBO;
import com.sns.post.domain.Post;
import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import com.sns.timeline.domain.CardDTO;
import com.sns.timeline.service.TimelineBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TimelineController {

    private final TimelineBO timelineBO;
    // private final PostBO postBO;
    // private final CommentBO commentBO;

    @GetMapping("/timeline")
    public String timeline(Model model, HttpSession session) {

        List<CardDTO> cardDTOList = timelineBO.generateCardList((Integer)session.getAttribute("userId"));
        model.addAttribute("cardList", cardDTOList);

//         // db select
//        List<PostEntity> postList = postBO.getPostList();
//        List<Comment> commentList = commentBO.getCommentList();
//
//         // model
//        model.addAttribute("postList", postList);
//        model.addAttribute("commentList", commentList);

        return "timeline/timeline";
    }
}
