import PageContainer from '../components/core/PageContainer';
import ContentBlock from '../components/core/ContentBlock';
import Title from '../components/core/Title';
import SubTitle from '../components/core/SubTitle';
import Button from '../components/core/Button';
import MediumTitle from '../components/core/MediumTitle';
import { useState } from 'react';
import { create, createLittleSurprise } from '../services/BetService';
import { createUser } from '../services/UserService';
import { useParams } from 'react-router';
import NavigationBar from '../components/core/NavigationBar';
import InputForm from '../components/core/InputForm';
import LabelForm from '../components/core/LabelForm';


function NewBet() {
    const params = useParams();
    const [name, setName] = useState("");
    const [cpf, setCpf] = useState("");
    const [numbers, setNumbers] = useState("");
    const [surprise, setSurprise] = useState(false);

    async function createBet(idsortition, numbers) {
        const user = await createUser(name, cpf)
        const bet = await create(idsortition, user.id, numbers);
        alert("Nova aposta criada com os seguintes números:" + bet.numbers)
        setName("")
        setNumbers("")
        setCpf("")
    }

    async function createSurpriseBet(idsortition) {
        const user = await createUser(name, cpf)
        await createLittleSurprise(idsortition, user.id);
        alert("Nova aposta criada com números aleatórios:")
        setName("")
        setNumbers("")
        setCpf("")
    }

    const validate = () => {
        if (cpf.length != 11) {
            alert("CPF deve ser uma sequencia de 11 números")
            return
        }

        if (name.length < 0 && name.length > 255) {
            alert("Nome não pode estar vazio")
            return
        }

        if (surprise) {
            createSurpriseBet(params.id);
            return true;
        } else {
            if (!numbers.match("^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$")) {
                alert("Os números devem ser separados por vírgula e conter exatamente 5 (cinco) números, com valores entre 1 e 50. Ex: 1,2,3,4,5 ou 10,20,30,40,50")
                return;
            } else {
                if (validateRange(numbers)) {
                    if (haveDuplicates(numbers)) {
                        alert("Apostas não podem contem números duplicados")
                        return;
                    } else {
                        alert("Apostas enviada para registro")
                        createBet(params.id, numbers);
                        return true;
                    }
                } else {
                    alert("Os números devem ser valores entre 1 a 50")
                    return;
                }
            }
        }
    }

    const validateRange = (numbers) => {
        const split = numbers.split(",");

        for (let n = 0; n < split.length; n++) {
            if (!verify(split[n])) {
                return false;
            }
        }

        return true;
    }

    const haveDuplicates = (numbers) => {
        const split = numbers.split(",");
        for (let i = 0; i < split.length; i++) {
            for (let j = i + 1; j < split.length; j++) {
                if (split[i] === split[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    const verify = (numero) => {
        const i = parseInt(numero)
        return i < 51 && i > 0
    }



    return (
        <PageContainer>
            <ContentBlock>
                <Title>BET MANAGER</Title>
                <MediumTitle>Formulário para registrar apostas no sorteio {params.id}</MediumTitle>
                <MediumTitle>Funcionalidade [Registrar nova aposta]</MediumTitle>
                <SubTitle>Preencha os dados abaixo e clique em registrar para criar uma nova aposta vinculada ao sorteio {params.id}</SubTitle>

                <LabelForm>Digite seu nome:
                    <InputForm type="text" value={name} onChange={(input) => setName(input.target.value)} />
                </LabelForm>

                <LabelForm>Digite seu CPF:
                    <InputForm type="text" value={cpf} onChange={(input) => setCpf(input.target.value)} />
                </LabelForm>

                <LabelForm>Digite os números:
                    <InputForm type="text" value={numbers} onChange={(input) => setNumbers(input.target.value)} />
                </LabelForm>

                <LabelForm>Surpresinha:
                    <input type="checkbox" value={surprise} onChange={(input) => setSurprise(!(surprise || false))} />
                </LabelForm>

                <NavigationBar>
                    <Button to={"/bet/" + params.id + "/" + params.statusSortition}>Voltar</Button>
                    <Button onClick={validate}>Registrar</Button>
                </NavigationBar>
            </ContentBlock>
        </PageContainer>
    )
}

export default NewBet;
