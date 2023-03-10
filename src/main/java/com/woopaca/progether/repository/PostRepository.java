package com.woopaca.progether.repository;

import com.woopaca.progether.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.writer")
    List<Post> findAllWithWriter();
}
