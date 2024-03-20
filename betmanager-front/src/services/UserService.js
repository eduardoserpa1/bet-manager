import axios from "axios";

const userAPI = axios.create({baseURL: "http://localhost:8080/user"});

async function getAllUsers(){
    const response = await userAPI.get("/");

    return response.data;
}

async function createUser(name, cpf){
    const body = {
        "name": name,
        "cpf": cpf
    }

    const response = await userAPI.post("/create", body);

    return response.data;
}

export{
    getAllUsers,
    createUser
}