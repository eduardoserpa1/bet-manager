import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import { useEffect, useState } from 'react';
import { setWinners } from '../services/ApurationService';
import { getAll } from '../services/SortitionService';
import { getAllBets } from '../services/BetService';
import { getAllUsers } from '../services/UserService';
import { useParams } from 'react-router';
import NavigationBar from '../components/core/NavigationBar';
import Table from '../components/core/Table';


function Result() {
    const params = useParams();
    const [sortition, setSortition] = useState();
    const [winners, setAllWinners] = useState([]);
    const [winnersAndBetsList, setAllWinnersAndBetsList] = useState([]);
    const [bets, setBets] = useState([]);
    const [numberInfo, setNumberInfo] = useState([]);

    
    

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

    function numberExist(arrayb,number){
        for (let i = 0; i < arrayb.length; i++) {
            if(arrayb[i][0] == number)
                return i;
        }
        return -1;

    }

    async function fetchSortitions() {
        const s = getById(await getAll());
        setSortition(s)
    }

    async function fetchBets(){
        const betResponse = await getAllBets();
        const betResponseFilter = betResponse.filter(bet => bet.idSortition == params.id)
        setBets(betResponseFilter)

        let infoString = ""

        for (let i = 0; i < betResponseFilter.length - 1; i++) {
            infoString += betResponseFilter[i].numbers + ",";
        }

        infoString += betResponseFilter[betResponseFilter.length - 1].numbers;

        const arrayInfoString = infoString.split(",");
        const arrayFinal = []

        let cont = 0;

        for (let i = 0; i < arrayInfoString.length; i++) {
            const index = numberExist(arrayFinal,arrayInfoString[i]);
            if(index < 0){
                arrayFinal.push( [arrayInfoString[i],1] )
            }else
            if(index >= 0){
                arrayFinal[index][1] = arrayFinal[index][1] + 1; 
            }
            
        }

        setNumberInfo(arrayFinal);
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
            fetchBets()
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
                <Table>
                <tr>
                    <td>Id da aposta</td>
                    <td>Jogador</td>
                    <td>Números apostados</td>
                </tr>
                {
                    winnersAndBetsList.length > 0 ? winnersAndBetsList.map(w => (<tr> <td>{w[2]}</td><td>{w[0]}</td><td>{w[3]}</td> </tr>)) : "Não houve vencedores"
                }
                </Table>


                <SubTitle>Números apostados no formato (Número : Quantidade de vezes apostado): </SubTitle>
                <Table>
                <tr>
                    <td>Número</td>
                    <td>Quantidade</td>
                </tr>
                {
                    numberInfo.length > 0 ? numberInfo.map(nf => (<tr><td>{nf[0]}</td><td>{nf[1]}</td></tr>)) : "Sem informações"
                }
                </Table>

                <SubTitle>Prêmios de cada ganhador: </SubTitle>
                <Table>
                <tr>
                    <td>Jogador</td>
                    <td>Prêmio super incrível</td>
                </tr>
                {
                    winnersAndBetsList.length > 0 ? winnersAndBetsList.map(w => (<tr> <td>{w[0]}</td> <td>{premiosIncriveis[Math.floor(Math.random() * premiosIncriveis.length)]}</td> </tr>)) : "Não houve vencedores"
                }
                </Table>

                <NavigationBar>
                    <Button to={"/apuration/" + params.id + "/" + params.statusSortition}>Voltar</Button>
                </NavigationBar>
            </ContentBlock>
        </PageContainer>
    )
}

export default Result;
