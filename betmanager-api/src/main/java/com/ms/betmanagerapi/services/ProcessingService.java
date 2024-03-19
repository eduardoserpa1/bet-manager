package com.ms.betmanagerapi.services;

import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Boolean haveWinners(SortitionModel sortitionModel, List<BetModel> bets){

        for (BetModel bet : bets){
            if(compareNumbers(sortitionModel.getNumbers(), bet.getNumbers()))
                return true;
        }

        return false;
    }

    public Boolean validadeNumbersIntegrity(String[] split){
        for(int i = 0; i < split.length; i++){
            int numberInteger = Integer.parseInt(split[i]);

            if(numberInteger < 1 || numberInteger > 50)
                return false;

            for (int j = i+1; j < split.length; j++)
                if(split[i].equals(split[j]))
                    return false;
        }

        return true;
    }

    public Boolean compareNumbers(String n1, String n2){
        String[] s1 = n1.split(",");
        String[] s2 = n2.split(",");
        
        int count = 0;

        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                if(s1[i].equals(s2[j])) {
                    count++;
                    j = s2.length;
                }
            }
        }
        
        if (count == 5)
            return true;
        
        return false;
    }


}
