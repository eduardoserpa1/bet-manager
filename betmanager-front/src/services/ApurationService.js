import axios from "axios";

const apurationAPI = axios.create({baseURL: "http://localhost:8080/apuration"});


async function makeApuration(id){
    const response = await apurationAPI.post(`/makeApuration/${id}`);

    return response.data;
}

async function setWinners(id){
    const response = await apurationAPI.post(`/setWinners/${id}`);

    return response.data;
}

export{
    makeApuration,
    setWinners
}