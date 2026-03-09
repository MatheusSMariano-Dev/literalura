package com.literalura.principal;

import com.literalura.dto.*;
import com.literalura.model.*;
import com.literalura.repository.*;
import com.literalura.service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){

        var opcao = -1;

        while(opcao != 0){

            System.out.println("""
                    
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores
4 - Listar autores vivos em determinado ano
5 - Listar livros por idioma
0 - Sair
""");

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){

                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresAno();
                case 5 -> listarLivrosIdioma();

            }

        }

    }

    private void buscarLivro(){

        System.out.println("Digite o nome do livro:");
        var titulo = leitura.nextLine();
        String url = "https://gutendex.com/books/?search=" + titulo.trim().replace(" ", "+");

        String json = consumo.obterDados(url);

        DadosResposta resposta = conversor.obterDados(json, DadosResposta.class);

        if(resposta.results().isEmpty()){
            System.out.println("Livro não encontrado.");
            return;
        }

        DadosLivro dadosLivro = resposta.results().get(0);

        if(dadosLivro.authors().isEmpty()){
            System.out.println("Autor não encontrado.");
            return;
        }

        DadosAutor dadosAutor = dadosLivro.authors().get(0);

        System.out.println("\n===== LIVRO ENCONTRADO =====");
        System.out.println("Título: " + dadosLivro.title());
        System.out.println("Autor: " + dadosAutor.name());
        System.out.println("Idioma: " + dadosLivro.languages().get(0));
        System.out.println("Downloads: " + dadosLivro.download_count());
        System.out.println("============================\n");

        Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.name());

        Autor autor;

        if(autorExistente.isPresent()){
            autor = autorExistente.get();
        }else{
            autor = new Autor(dadosAutor.name(), dadosAutor.birth_year(), dadosAutor.death_year());
            autorRepository.save(autor);
        }

        Livro livro = new Livro(
                dadosLivro.title(),
                dadosLivro.languages().get(0),
                dadosLivro.download_count(),
                autor
        );

        livroRepository.save(livro);

        System.out.println("Livro salvo com sucesso!");

    }

    private void listarLivros(){

        List<Livro> livros = livroRepository.findAll();

        livros.forEach(l ->
                System.out.println(l.getTitulo() + " - " + l.getAutor().getNome())
        );

    }

    private void listarAutores(){

        List<Autor> autores = autorRepository.findAll();

        autores.forEach(a ->
                System.out.println(a.getNome())
        );

    }

    private void listarAutoresAno(){

        System.out.println("Digite o ano:");
        var ano = leitura.nextInt();

        List<Autor> autores =
                autorRepository.findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(ano,ano);

        autores.forEach(a ->
                System.out.println(a.getNome())
        );

    }

    private void listarLivrosIdioma(){

        System.out.println("Digite o idioma:");
        var idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        livros.forEach(l ->
                System.out.println(l.getTitulo())
        );

    }

}