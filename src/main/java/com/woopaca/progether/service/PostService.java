package com.woopaca.progether.service;

import com.woopaca.progether.controller.dto.PostListResponseDto;

import java.util.List;

public interface PostService {

    List<PostListResponseDto> postList();
}
