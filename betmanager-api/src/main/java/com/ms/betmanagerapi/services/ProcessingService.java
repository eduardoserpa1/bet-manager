package com.ms.betmanagerapi.services;

import org.springframework.stereotype.Service;

@Service
public class ProcessingService {

    public String getLittleSurprise(){
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            str.append((int) (Math.random() * 50) + 1);
            str.append(",");
        }
        str.append((int) (Math.random() * 50) + 1);

        return str.toString();
    }

}
