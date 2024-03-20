import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';


function Welcome(){
    return(
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <SubTitle>Bem vindo ao Bet Manager, clique no botão abaixo para ser redirecionado para a plataforma de sorteios</SubTitle>
                <Button to={"/home"}>Iniciar</Button>
            </ContentBlock>
        </PageContainer>
    )
}

export default Welcome;
