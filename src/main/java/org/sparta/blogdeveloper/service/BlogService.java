package org.sparta.blogdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.sparta.blogdeveloper.domain.Article;
import org.sparta.blogdeveloper.dto.AddArticleRequest;
import org.sparta.blogdeveloper.dto.UpdateArticleRequest;
import org.sparta.blogdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional //트렌젝션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
