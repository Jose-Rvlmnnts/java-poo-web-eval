package com.java.eval.web.repository;

import com.java.eval.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Album findByTitleIgnoreCase(String title);
}
