import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import { makeApuration, setWinners } from '../services/ApurationService';
import { useParams } from 'react-router';
import NavigationBar from '../components/core/NavigationBar';


function Apuration() {
    const params = useParams();

    async function apure() {
        await makeApuration(params.id);
        await setWinners(params.id);
        alert("Apuração feita com sucesso do sorteio de número " + params.id);
    }

    const make = () => {
        apure()
    }


    return (
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <MediumTitle>Apuração do sorteio {params.id}</MediumTitle>
                <MediumTitle>Funcionalidades [Finalizar apostas e executar o sorteio] e [Fim da Apuração]</MediumTitle>
                <SubTitle>Aqui você pode finalizar apostas e executar o sorteio ao clicar no botão Apurar {params.id}</SubTitle>
                <SubTitle>Ao clicar no botão resultados, é exibido um relatório com todas as informações solicitadas na funcionalidade [Fim da Apuração]</SubTitle>

                <NavigationBar>
                    <Button to={"/bet/" + params.id + "/" + params.statusSortition}>Voltar</Button>
                    {
                        params.statusSortition == "true"
                        ?
                        <Button to={"/result/" + params.id + "/" + params.statusSortition}>Resultados</Button>
                        :
                        null
                    }
                    {
                        params.statusSortition == "false"
                        ?
                        <Button to={"/apuration/" + params.id + "/" + true} onClick={make}>Finalizar</Button>
                        :
                        null
                    }
                </NavigationBar>
            </ContentBlock>
        </PageContainer>
    )
}

export default Apuration;
