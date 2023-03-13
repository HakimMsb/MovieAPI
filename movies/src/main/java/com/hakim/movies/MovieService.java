package com.hakim.movies;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	private MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public List<Movie> allMovies(){
		return movieRepository.findAll();
	}
	
	public Optional<Movie> singleMovie(String imdbId) {
		return movieRepository.findMovieByImdbId(imdbId);	
	}
}
