package com.example.proekt.config;

import com.example.proekt.model.*;
import com.example.proekt.repository.jpa.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final MovieRepository movieRepository;
    private final PerformanceRepository performanceRepository;
    private final DirectorRepository directorRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final ArtRepository artRepository;

    public DataInitializer(MovieRepository fIlmRepository, PerformanceRepository performanceRepository, DirectorRepository directorRepository, UserRepository userRepository, ArtistRepository artistRepository, ArtRepository artRepository) {
        this.movieRepository = fIlmRepository;
        this.performanceRepository = performanceRepository;
        this.directorRepository = directorRepository;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.artRepository = artRepository;
    }

    @PostConstruct
    public void initData() {
        Director director1 = new Director();
        director1.setName("Aloon nee");
        director1.setSurname("N");
        Director director2 = new Director();
        director2.setName("Jerry Zaks");
        director2.setSurname("");

        Director director3 = new Director();
        director3.setName("Brendon Fox");
        director3.setSurname("");

        Movie film1=new Movie();
        film1.setName("Lost City");
        film1.setDate("03.05.2022 18:00");
        film1.setPrice(25.02);
        film1.setType(MovieGenre.COMEDY);
        film1.setDirector(this.directorRepository.save(director1));
        film1.setImageUrl("https://i0.wp.com/www.artofvfx.com/wp-content/uploads/2021/12/lost_city_xlg.jpg?ssl=1");
        movieRepository.save(film1);
        Movie film2=new Movie();
        film2.setName("The Batman");
        film2.setDate("04.05.2022 22:00");
        film2.setPrice(30.00);
        film2.setType(MovieGenre.ACTION);
        film2.setDirector(this.directorRepository.save(director1));
        film2.setImageUrl("https://m.media-amazon.com/images/M/MV5BOGE2NWUwMDItMjA4Yi00N2Y3LWJjMzEtMDJjZTMzZTdlZGE5XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg");
        movieRepository.save(film2);

        Artist artist1 = new Artist();
        artist1.setName("Leonardo da Vinci");


        Art art1= new Art();
        art1.setName("Mona Lisa");
        art1.setDescription("Oil painting by Italian artist");
        art1.setPrice(500.00);
        art1.setArtist(this.artistRepository.save(artist1));
        art1.setImageUrl("https://uploads0.wikiart.org/00339/images/leonardo-da-vinci/mona-lisa-c-1503-1519.jpg");
        artRepository.save(art1);

        Performance performance1=new Performance();
        performance1.setName("Assassins and Company");
        performance1.setDate("04.05.2022 21:00");
        performance1.setPrice(50.6);
        performance1.setDirector(this.directorRepository.save(director2));
        performance1.setType(PerformanceGenre.MUSICAL);
        performance1.setImageUrl("https://m.media-amazon.com/images/I/51jBTDtVPQL.jpg");
        performanceRepository.save(performance1);
        Performance performance2=new Performance();
        performance2.setName("The Pitmen Painters");
        performance2.setDirector(this.directorRepository.save(director3));
        performance2.setDate("06.05.2022 19:00");

        performance2.setPrice(20.5);
        performance2.setType(PerformanceGenre.TRAGEDY);
        performance2.setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/be/Pitmenpainters.jpg");
        performanceRepository.save(performance2);

        User user1 = new User();
        user1.setUsername("wer");
        user1.setPassword("qwe");
        user1.setName("asd");
        user1.setSurname("zxc");
        user1.setRole(Role.ROLE_ADMIN);
        userRepository.save(user1);






    }
}
