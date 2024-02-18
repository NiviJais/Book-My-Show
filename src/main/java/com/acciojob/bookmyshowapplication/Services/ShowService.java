package com.acciojob.bookmyshowapplication.Services;

import com.acciojob.bookmyshowapplication.Entities.*;
import com.acciojob.bookmyshowapplication.Enums.SeatType;
import com.acciojob.bookmyshowapplication.Exceptions.InvalidMovieException;
import com.acciojob.bookmyshowapplication.Repository.MovieRepository;
import com.acciojob.bookmyshowapplication.Repository.ShowRepository;
import com.acciojob.bookmyshowapplication.Repository.ShowSeatRepository;
import com.acciojob.bookmyshowapplication.Repository.TheaterRepository;
import com.acciojob.bookmyshowapplication.Requests.AddShowRequest;
import com.acciojob.bookmyshowapplication.Requests.AddShowSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.prefs.InvalidPreferencesFormatException;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    public String addShow(AddShowRequest showRequest)throws Exception{
        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());

        if(movie == null){
            throw new InvalidMovieException("Movie Name entered does not exist in the DB");
        }

        Optional<Theater> theaterOptional = theaterRepository.findById(showRequest.getTheaterId());
        if(theaterOptional.isEmpty()){
            throw new InvalidPreferencesFormatException("Theater Id entered is incorrect");
        }
        Theater theater = theaterOptional.get();

        //Ideally you should create the show Entity with the help of Transformers
        Show showEntity = new Show(showRequest.getShowDate(),showRequest.getShowTime());

        showEntity.setMovie(movie);
        showEntity.setTheater(theater);

        //Bidirectional in the parent class
        movie.getShowList().add(showEntity);
        theater.getShowList().add(showEntity);
        showEntity = showRepository.save(showEntity);
        return "The Show has been created with the showId "+ showEntity.getShowId();
    }

    public String addShowSeats(AddShowSeatsRequest showSeatsRequest){
        Show show = showRepository.findById(showSeatsRequest.getShowId()).get();
        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeat theaterSeat: theaterSeatList){
            String seatNo = theaterSeat.getSeatNo();
            SeatType seatType = theaterSeat.getSeatType();

            ShowSeat showSeat = ShowSeat.builder()
                    .foodAttached(false)
                    .isAvailable(true)
                    .show(show)
                    .seatNo(seatNo)
                    .seatType(seatType)
                    .build();

            if(seatType.equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatsRequest.getPriceForClassicSeats());
            }else{
                showSeat.setPrice(showSeatsRequest.getPriceForPremiumSeats());
            }

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);
        return "Show seats has been added to system";
    }
}
