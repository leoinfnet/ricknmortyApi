package br.com.infnet;

import br.com.infnet.model.*;
import br.com.infnet.util.ConsoleUtil;
import br.com.infnet.util.EpisodioUtil;
import br.com.infnet.util.LocationUtil;
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

        ConsoleUtil.menuPrincipal();
        Scanner scanner = new Scanner(System.in);
        EpisodioUtil episodioUtil = new EpisodioUtil();
        PersonagemUtil personagemUtil = new PersonagemUtil();
        int opcao = scanner.nextInt();
        switch (opcao){
            case 1: {
                getEpisodio();
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
            case 6: {
                LocationUtil locationUtil = new LocationUtil();
                PayloadLocationsList allLocations = locationUtil.getAllLocations();
                System.out.println("Total de Locations: " + allLocations.getInfo().getCount());
                int page = 1;
                System.out.println("Page: " + page);
                List<Location> results = allLocations.getResults();
                results.forEach(location -> {
                    System.out.println("ID: " + location.getId());
                    System.out.println("Nome: " + location.getName());
                    System.out.println("Type: " + location.getType());
                    System.out.println("======================");
                });
                System.out.println("(A) -> Anterior ; (P) -> Proxima ; (S) -> Sair");
                String escolhaPaginacao = scanner.next();
                while (!escolhaPaginacao.equalsIgnoreCase("S") ) {
                    switch (escolhaPaginacao){
                        case "a": {
                            allLocations = locationUtil.getAllLocations(page--);
                            System.out.println("Page: " + page);
                            results = allLocations.getResults();
                            results.forEach(location -> {
                                System.out.println("ID: " + location.getId());
                                System.out.println("Nome: " + location.getName());
                                System.out.println("Type: " + location.getType());
                                System.out.println("======================");
                            });
                            break;
                        }
                        case "p": {
                            allLocations = locationUtil.getAllLocations(++page);
                            System.out.println("Page: " + page);
                            results = allLocations.getResults();
                            results.forEach(location -> {
                                System.out.println("ID: " + location.getId());
                                System.out.println("Nome: " + location.getName());
                                System.out.println("Type: " + location.getType());
                                System.out.println("======================");
                            });
                            break;
                        }
                        default: break;

                    }
                    System.out.println("(A) -> Anterior ; (P) -> Proxima ; (S) -> Sair");
                    escolhaPaginacao = scanner.next();
                }
                break;
            }
        }
    }

    private static void getEpisodio() {
        Scanner scanner = new Scanner(System.in);
        EpisodioUtil episodioUtil = new EpisodioUtil();
        PersonagemUtil personagemUtil = new PersonagemUtil();
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
    }


}
