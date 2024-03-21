import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import ListBlock from '../components/core/ListBlock';
import ListItem from '../components/core/ListItem';
import { useEffect, useState } from 'react';
import { makeApuration, setWinners } from '../services/ApurationService';
import { getAll } from '../services/SortitionService';
import { getAllUsers } from '../services/UserService';
import { useParams } from 'react-router';
import NavigationBar from '../components/core/NavigationBar';


function Result() {
    const params = useParams();
    const [sortition, setSortition] = useState();
    const [winners, setAllWinners] = useState([]);
    const [winnersAndBetsList, setAllWinnersAndBetsList] = useState([]);

    const premiosIncriveis = ['Caneta sem tinta',
        'Olho mágico para porta de vidro',
        'Quebrador de ovos',
        'Guardador de bananas',
        'Rebobinador de DVD',
        'Sapato sem sola',
        'Guarda chuva para sapatos',
        'Andador para peixes',
        'Pedra USB']

    function getById(sr) {
        for (let i = 0; i < sr.length; i++) {
            if (sr[i].id == params.id) {
                return sr[i];
            }
        }
        return null;
    }

    async function fetchSortitions() {
        const s = getById(await getAll());
        setSortition(s)
    }

    async function fetchWinners() {
        const winners = await setWinners(params.id);
        setAllWinners(winners)
    }

    async function fetchWinnersAndBetsList() {
        await fetchWinners();
        const users = await getAllUsers();

        let orderListOfUserBets = []

        for (let i = 0; i < winners.length; i++) {
            let user;

            for (let j = 0; j < users.length; j++) {
                if (winners[i].idUser === users[j].id) {
                    user = users[j];
                    break;
                }
            }

            const objJson = [user.name, user.cpf, winners[i].id, winners[i].numbers]

            orderListOfUserBets.push(objJson);
        }



        setAllWinnersAndBetsList(orderListOfUserBets);
    }

    useEffect(
        () => {
            fetchSortitions()
        },
        []
    )

    useEffect(
        () => {
            fetchWinners()
            fetchWinnersAndBetsList()
        },
        [sortition]
    )



    return (
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <MediumTitle>Resultados da apuração do sorteio {params.id}</MediumTitle>
                <MediumTitle>Funcionalidades [Fim da apuração] e [Premiação]</MediumTitle>
                <SubTitle>Números sorteados:</SubTitle>
                {
                    sortition != null ? <SubTitle textcolor='black'>{sortition.numbers}</SubTitle> : null
                }

                <SubTitle>Rodadas:</SubTitle>
                {
                    sortition != null ? <SubTitle textcolor='black'>{sortition.numbers.split(",").length - 4}</SubTitle> : null
                }

                <SubTitle>Quantidade de apostas vencedoras:</SubTitle>
                {
                    winners.length != null ? <SubTitle textcolor='black'>{winners.length}</SubTitle> : -1
                }
                
                <SubTitle>Lista de apostas vencedoras </SubTitle>
                {
                    winnersAndBetsList.length > 0 ? winnersAndBetsList.map(w => (<SubTitle textcolor='black'>Aposta {w[2]}: {w[0]} - {w[3]}</SubTitle>)) : "Não houve vencedores"
                }

                <SubTitle>Números apostados e quantidade de apostas: </SubTitle>
                {

                }

                <SubTitle>Prêmios de cada ganhador: </SubTitle>
                {
                    winnersAndBetsList.length > 0 ? winnersAndBetsList.map(w => (<SubTitle textcolor='black'>Jogador {w[0]} ganhou um(a) incrível {premiosIncriveis[Math.floor(Math.random() * premiosIncriveis.length)]}</SubTitle>)) : "Não houve vencedores"
                }

                <NavigationBar>
                    <Button to={"/apuration/" + params.id}>Voltar</Button>
                </NavigationBar>
            </ContentBlock>
        </PageContainer>
    )
}

export default Result;
