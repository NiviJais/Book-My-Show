package com.acciojob.bookmyshowapplication.Services;

import com.acciojob.bookmyshowapplication.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public String addShow(AddShowRequest addShowRequest){

    }
}
