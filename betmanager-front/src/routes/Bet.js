import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import ListBlock from '../components/core/ListBlock';
import ListItem from '../components/core/ListItem';
import { useEffect, useState } from 'react';
import { getAllBets } from '../services/BetService';
import { useParams } from 'react-router';
import NavigationBar from '../components/core/NavigationBar';


function Bet(){
    const [bets, setBets] = useState([]);
    const params = useParams();

    async function fetchBets(){
        const betResponse = await getAllBets();
        const betResponseFilter = betResponse.filter(bet => bet.idSortition == params.id)
        setBets(betResponseFilter)
    }

    useEffect(
        () => {
            fetchBets()
        },
        []
    )

    return(
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <MediumTitle>Lista de apostas do sorteio {params.id} - Funcionalidade [Registrar nova aposta]</MediumTitle>
                <SubTitle>Aqui você pode visualizar todas as apostas do sorteio {params.id}</SubTitle>
                <SubTitle>Clique em nova aposta para criar um novo palpite para quais serão os números sorteados nessa edição.</SubTitle>
                
                <ListBlock>
                    <MediumTitle>Apostas</MediumTitle>
                    { 
                        bets.length !== 0 ? bets.map( bet => (
                            <ListItem routerequest={"bet"} idrequest={params.id} sortitionstatus={params.statusSortition} name={"Aposta "+bet.id} text={bet.numbers}/>
                        )) : null
                    }
                </ListBlock>
                
                <NavigationBar>
                    <Button to={"/home"}>Voltar</Button>
                    
                    {   
                        bets.length > 0 
                        ?
                            params.statusSortition == "true" 
                            ? 
                            <Button to={"/result/" + params.id + "/" + params.statusSortition}>Resultados</Button> 
                            : 
                            <Button to={"/apuration/" + params.id + "/" + params.statusSortition}>Apuração</Button>
                        :
                        null
                    }

                    {   
                        params.statusSortition == "false" 
                        ? 
                        <Button to={"/newbet/"+params.id+"/"+params.statusSortition}>Nova aposta</Button>
                        : 
                        null
                    }
                    
                </NavigationBar>
            </ContentBlock>
        </PageContainer>
    )
}

export default Bet;
