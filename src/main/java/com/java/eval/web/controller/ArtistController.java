package com.java.eval.web.controller;

import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist findById(@PathVariable(value = "id")Integer id){
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isEmpty()){
            throw new EntityNotFoundException("L'artiste de cet id n'existe pas");
        }
        return artist.get();
    }

    @RequestMapping(value = "", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,params = "name")
    public List<Artist> findByName(@RequestParam("name") String name){
        return artistRepository.findByNameContainingIgnoreCase(name);
    }


    @RequestMapping(value = "", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> listArtists(@RequestParam Integer page, @RequestParam Integer size,
                                    @RequestParam String sortProperty, @RequestParam String sortDirection){
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist newArtist(@RequestBody Artist artist){
        if (artistRepository.findByNameIgnoreCase(artist.getName()) != null){
            throw new EntityExistsException("cet artiste est déjà enregistré");
        }
        return artistRepository.save(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(@RequestBody Artist artist){
        return artistRepository.save(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id){
        artistRepository.deleteById(id);
    }

}
