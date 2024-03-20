import axios from "axios";

const betAPI = axios.create({baseURL: "http://localhost:8080/bet"});

async function getAll(){
    const response = await betAPI.get("/");

    return response.data;
}

async function create(idsortition,iduser,numbers){
    const body = {
        "idSortition": idsortition,
        "idUser": iduser,
        "numbers": numbers
    }

    const response = await betAPI.post("/create", body);

    return response.data;
}

export{
    getAll,
    create
}