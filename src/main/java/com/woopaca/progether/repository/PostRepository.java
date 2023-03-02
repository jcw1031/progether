package com.woopaca.progether.repository;

import com.woopaca.progether.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
