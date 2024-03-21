import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import ListBlock from '../components/core/ListBlock';
import ListItem from '../components/core/ListItem';
import { useEffect, useState } from 'react';
import { getAll, createRandom } from '../services/SortitionService';


function Home(){
    const [sortitions, setSortitions] = useState([]);

    async function fetchSortitions(){
        const sortitionResponse = await getAll();
        setSortitions(sortitionResponse)
    }

    async function createRandomSortition(){
        const randomSortitionResponse = await createRandom();
        alert("Novo sorteio criado com id = " + randomSortitionResponse.id)
        await fetchSortitions();
    }

    useEffect(
        () => {
            fetchSortitions()
        },
        []
    )

    return(
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <MediumTitle>Lista de sorteios - Funcionalidade [Iniciar]</MediumTitle>
                <SubTitle>Aqui você pode visualizar todos os sorteios, como também o status do mesmo para saber se ainda aceitam apostas ou se já foram apuradas.</SubTitle>
                <SubTitle>Clique em novo para criar um novo sorteio, ou então clique no sorteio desejado para ver/criar apostas.</SubTitle>
                
                <ListBlock>
                    <MediumTitle>Sorteios</MediumTitle>
                    { 
                        sortitions.length !== 0 ? sortitions.map( sortition => (
                            <ListItem routerequest={"bet"} idrequest={sortition.id} sortitionstatus={sortition.isFinished.toString()} name={"Sorteio número "+sortition.id} text={sortition.isFinished.toString()}/>
                        )) : null
                    }
                </ListBlock>
                <Button onClick={createRandomSortition}>Iniciar novo sorteio</Button>
            </ContentBlock>
        </PageContainer>
    )
}

export default Home;
