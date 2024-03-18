package com.ms.betmanagerapi.services;

import org.springframework.stereotype.Service;

@Service
public class ProcessingService {

    public String getLittleSurprise(){
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            str.append(getRandomNumber());
            str.append(",");
        }
        str.append(getRandomNumber());

        return str.toString();
    }

    public String getRandomNumber(){
        return String.valueOf((int) (Math.random() * 50) + 1);
    }



}
