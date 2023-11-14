package br.com.infnet;

import br.com.infnet.model.Episodio;
import br.com.infnet.model.PayloadPersonagemList;
import br.com.infnet.model.Personagem;
import br.com.infnet.util.EpisodioUtil;
import br.com.infnet.util.PersonagemUtil;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Bem vindo a API de Rick N Morty");
        System.out.println("Digite a Opcao Desejada");
        System.out.println(" 1 - Buscar Episodio");
        System.out.println(" 2 - Buscar Avatar");
        System.out.println(" 3 - Buscar Personagem pelo ID");
        System.out.println(" 4 - Buscar Personagem pelo nome");
        System.out.println(" 5 - Buscar Avatar pelo nome");
        Scanner scanner = new Scanner(System.in);
        EpisodioUtil episodioUtil = new EpisodioUtil();
        PersonagemUtil personagemUtil = new PersonagemUtil();
        int opcao = scanner.nextInt();
        switch (opcao){
            case 1: {
                System.out.println("Digite o numero do episodio");
                int episodioId = scanner.nextInt();
                Episodio episodio = episodioUtil.getById(episodioId);
                List<Personagem> personagens = personagemUtil.getPersonagens(episodio.getCharacters(), 3);
                System.out.println("Nome: " + episodio.getName());
                System.out.println("Data que foi ao ar': " + episodio.getAirDate());
                System.out.println("Personagens: ");
                personagens.forEach(personagem -> {

                    System.out.println("Nome: " + personagem.getName());
                    System.out.println("Status: " + personagem.getStatus());
                    System.out.println("Origem: " + personagem.getOrigin().getName());
                    System.out.println("======================");
                });

                break;
            }
            case 2: {
                System.out.println("Digite o id do Personagem");
                int personagemId = scanner.nextInt();
                personagemUtil.getAvatar(personagemId);
                System.out.println("Imagem baixada");
                break;

            }
            case 3: {
                System.out.println("Digite o id do Personagem");
                int personagemId = scanner.nextInt();
                System.out.println("Buscando personagem... Aguarde");
                Personagem personagem = personagemUtil.getPersonagem(personagemId);
                System.out.println("Nome: " + personagem.getName());
                System.out.println("Status: " + personagem.getStatus());
                System.out.println("Origem: " + personagem.getOrigin().getName());
                System.out.println("======================");
                break;

            }
            case 4: {
                System.out.println("Digite o nome do Personagem");
                String personagemName = scanner.next();
                System.out.println("Buscando personagem... Aguarde");
                PayloadPersonagemList payloadPersonagemList = personagemUtil.buscarPeloNome(personagemName);
                List<Personagem> results = payloadPersonagemList.getResults();
                System.out.println("Total de Resultados: " + payloadPersonagemList.getInfo().getCount());
                results.forEach(personagem -> {
                    System.out.println("Nome: " + personagem.getName());
                    System.out.println("Status: " + personagem.getStatus());
                    System.out.println("Origem: " + personagem.getOrigin().getName());
                    System.out.println("======================");
                });
                break;

            }
            case 5: {
                System.out.println("Digite o nome do Personagem");
                String personagemName = scanner.next();
                personagemUtil.getAvatar(personagemName);
                System.out.println("Imagem baixada");
                break;
            }
        }

    }
}
