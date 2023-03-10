package com.woopaca.progether.service.impl;

import com.woopaca.progether.controller.dto.PostListResponseDto;
import com.woopaca.progether.repository.PostRepository;
import com.woopaca.progether.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicPostService implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostListResponseDto> postList() {
        List<PostListResponseDto> postList = new ArrayList<>();
        postRepository.findAllWithWriter().forEach(post ->
                postList.add(post.toListDto()));
        return postList;
    }
}
