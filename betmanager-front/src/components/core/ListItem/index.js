import { Link } from "react-router-dom";
import styled from "styled-components"

const ListItemContainer = styled(Link)`
    width: 95%;
    height: 50px;

    margin-top: 5px;
    margin-bottom: 5px;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;

    background-color: #008a4b;
    text-decoration: none;

    font-weight: bold;

    &:hover{
        cursor: pointer;
        background-color: #01703d;
    }
`

const ListItemName = styled.span`
    padding-left: 50px;
    color: white;
`

const ListItemStatus = styled.span`
    padding-right: 50px;
    
    color: ${props => props.textcolor || 'white'};
`

function ListItem(props){
    let statusColor = 'white';
    let statusText = props.text;

    if(props.text === "false"){
        statusColor = '#00ff04';
        statusText = 'ABERTO';
    }
    else if(props.text === "true"){
        statusColor = '#ff2f00';
        statusText = 'FINALIZADO';
    }
        

    
    const idRequest = props.idrequest;
    const routeRequest = props.routerequest;
    
    return(
        <ListItemContainer to={"/"+ routeRequest + "/" + idRequest}>
            <ListItemName>{props.name}</ListItemName>
            <ListItemStatus textcolor={statusColor}>{statusText}</ListItemStatus>
        </ListItemContainer>
    )
}

export default ListItem;