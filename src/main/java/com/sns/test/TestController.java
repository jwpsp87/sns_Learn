package com.sns.test;

import com.sns.post.domain.Post;
import com.sns.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private PostMapper postMapper;

    @ResponseBody
    @GetMapping("/sns_test1")
    public String test1() {
        return "<h2>SNS 테스트</h2>";
    }

    @ResponseBody
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 2222);
        map.put("b", 222222);
        map.put("c", 333 );

        return map;
    }

    @GetMapping("/test3")
    public String test3() {
        return "test/hello";
    }

    @ResponseBody
    @GetMapping("/test4")
    public List<Post> test4() {
        return postMapper.selectPostListTest();
    }
}
