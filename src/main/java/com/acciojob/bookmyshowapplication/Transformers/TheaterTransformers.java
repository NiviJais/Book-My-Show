package com.acciojob.bookmyshowapplication.Transformers;

import com.acciojob.bookmyshowapplication.Entities.Theater;
import com.acciojob.bookmyshowapplication.Requests.AddTheaterRequest;

public class TheaterTransformers {

    public static Theater convertRequestToEntity(AddTheaterRequest addTheaterRequest){

        Theater theater = Theater.builder()
                        .address(addTheaterRequest.getAddress())
                        .noOfScreens(addTheaterRequest.getNoOfScreens())
                        .name(addTheaterRequest.getName())
                        .build();

        return theater;
    }
}
