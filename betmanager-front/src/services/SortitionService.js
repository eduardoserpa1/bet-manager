import axios from "axios";

const sortitionAPI = axios.create({baseURL: "http://localhost:8080/sortition"});

async function getAll(){
    const response = await sortitionAPI.get("/");

    return response.data;
}

async function createRandom(){
    const body = {
        "numbers": "1,1,1,1,1",
        "isFinished": false
    }

    const response = await sortitionAPI.post("/createRandom", body);

    return response.data;
}

export{
    getAll,
    createRandom
}