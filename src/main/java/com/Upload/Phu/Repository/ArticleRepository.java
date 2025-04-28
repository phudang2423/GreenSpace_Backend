package com.Upload.Phu.Repository;

import com.Upload.Phu.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {}
