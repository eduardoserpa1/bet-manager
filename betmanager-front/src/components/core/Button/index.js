import styled from 'styled-components'
import { Link } from 'react-router-dom';

const Button = styled(Link)`
    font-size: 24px;
    font-weight: bold;
    font-family: 'Roboto';

    background-color: #2ac982;
    text-decoration: none;
    padding: 20px 50px 20px 50px;

    border-radius: 20px;

    margin:2% 10% 2% 10%;

    color: white;
    text-shadow: 0px 0px 20px #007a43;

    box-shadow: 0px 0px 10px #007a43;

    &:hover{
        color: #e0e0e0;
        background-color: #26b575;
        cursor: pointer;
    }
`
//'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue'

export default Button;