package com.woopaca.progether;

import com.woopaca.progether.entity.Comment;
import com.woopaca.progether.entity.Post;
import com.woopaca.progether.entity.User;
import com.woopaca.progether.repository.CommentRepository;
import com.woopaca.progether.repository.PostRepository;
import com.woopaca.progether.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CascadeTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void cascadeTest() throws Exception {
        //given
        User user = User.builder()
                .email("test@test")
                .password("test")
                .name("test")
                .build();
        User anotherUser = User.builder()
                .email("test2@test")
                .password("test")
                .name("test")
                .build();
        userRepository.save(user);
        userRepository.save(anotherUser);

        Post post = Post.builder().
                postTitle("test")
                .postContent("test입니다.")
                .build();
        Post anotherPost = Post.builder().
                postTitle("test")
                .postContent("test입니다.")
                .build();
        post.writePost(user);
        anotherPost.writePost(anotherUser);
        postRepository.save(post);
        postRepository.save(anotherPost);

        Comment comment1 = Comment.builder()
                .commentContent("test1")
                .build();
        Comment comment2 = Comment.builder()
                .commentContent("test2")
                .build();
        Comment comment3 = Comment.builder()
                .commentContent("test3")
                .build();
        comment1.writeComment(user, post);
        comment2.writeComment(user, post);
        comment3.writeComment(user, anotherPost);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        //when
        userRepository.delete(user);

        //then

    }
}
