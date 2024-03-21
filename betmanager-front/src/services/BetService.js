import axios from "axios";

const betAPI = axios.create({baseURL: "http://localhost:8080/bet"});

async function getAllBets(){
    const response = await betAPI.get("/");

    return response.data;
}

async function create(idsortition,iduser,numbers){
    const body = {
        "idSortition": idsortition,
        "idUser": iduser,
        "numbers": numbers,
        "isWinner": false
    }

    const response = await betAPI.post("/create", body);

    return response.data;
}

async function createLittleSurprise(idsortition,iduser){
    const body = {
        "idSortition": idsortition,
        "idUser": iduser,
        "numbers": "1,1,1,1,1",
        "isWinner": false
    }

    const response = await betAPI.post("/createLittleSurprise", body);

    return response.data;
}

export{
    getAllBets,
    create,
    createLittleSurprise
}