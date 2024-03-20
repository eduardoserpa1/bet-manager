import styled from 'styled-components'
import ContentBlock from '../components/core/ContentBlock'


const HomeContainer = styled.div`
    width: 100vw;
    height: 100vh;
    background-color: white;

    display:flex;
    justify-content: center;
    align-itens: center; 

    background-color: #66ad8d;
`

function Home(){
    return(
        <HomeContainer>
            <ContentBlock>
                titulo
            </ContentBlock>
        </HomeContainer>
    )
}

export default Home;
