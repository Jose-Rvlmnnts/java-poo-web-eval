package com.java.eval.web.repository;

import com.java.eval.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    List<Artist> findByNameContainingIgnoreCase(String name);
    Artist findByNameIgnoreCase(String name);

}
