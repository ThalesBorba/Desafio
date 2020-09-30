import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ResumoJornal extends JornalResponse {

    private ArrayList<Jornal> jornais;

    ResumoJornal(ArrayList<Jornal> jornais) {
        this.jornais = jornais;
    }

    //Definindo país
    Locale localeBR = new Locale("pt", "BR");

    //Transfoma número em moeda desse país
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

    /*Formata a data. E = dia da semana. d/M/y = dia/mês/ano. A quantidade que repete é para definir
    se vc quer por extenso, ou abreviado, tipo: 7 / 07 / jul / julho. Se vc quer escrever algo não previsto
    é só colocar no meio de aspas simples.*/
    DateFormat dataBR = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' y");

    //size retorna o tamanho da lista
    public int quantidadeDeJornaisDaLista() {
        return jornais.size();
    }

    public Jornal getJornalMaisCaro() {
        double maiorPreco = 0;

        //pega o primeiro jornal para iniciar a comparação
        Jornal jornalCorrespondente = jornais.get(0);

        //Para cada jornal...
        for (Jornal jornal : jornais) {

            //se o preço for maior que o que está atualmente na variável
            if (jornal.getPreco() > maiorPreco) {

                //essa variável recebe o preço atual
                maiorPreco = jornal.getPreco();

                //essa recebe o jornal atual
                jornalCorrespondente = jornal;
            }
        }
        return jornalCorrespondente;
    }

    public String getSomaDosPrecosDosJornaisEmPortugues() {
        double precoTotal = 0;
        for (Jornal jornal : jornais) {
            if (jornal.getLingua().equals("Português")) {
                precoTotal += jornal.getPreco();
            }
        }
        return dinheiro.format(precoTotal);
    }


    public String getTotalDePrecosDosJornais() {
        double precoTotal = 0;
        for (Jornal jornal : jornais) {
            precoTotal += jornal.getPreco();
        }
        return dinheiro.format(precoTotal);
    }


    public String getJornalMaisCaroDiferenteDePortugues() {
        double precoTotal = 0;
        for (Jornal jornal : jornais) {
            if (!jornal.getLingua().equals("Português")) {
                precoTotal += jornal.getPreco();
            }
        }
        return dinheiro.format(precoTotal);
    }

    public String getJornalMaisAntigo() {

        //data de publicação do primeiro jornal
        Date dataMaisAntiga = jornais.get(0).getDataDePublicacao();
        String jornalMaisAntigo = jornais.get(0).getNomeDoJornal();
        for (Jornal jornal : jornais ) {

            //se o jornal for mais antigo...
            if (jornal.getDataDePublicacao().before(dataMaisAntiga)) {
                dataMaisAntiga = jornal.getDataDePublicacao();
                jornalMaisAntigo = jornal.getNomeDoJornal();
            }
        }

        //Formata a data de acordo com a variável lá em cima
        String dataRetorno = dataBR.format(dataMaisAntiga);

        //Quebra a String em duas para poder tornar só a primeira letra maiúscula
        return jornalMaisAntigo + ", " + dataRetorno.substring(0, 1).toUpperCase() + dataRetorno.substring(1);
    }

    public Jornal getJornalMaisRecente() {
        Date dataMaisRecente = jornais.get(0).getDataDePublicacao();
        Jornal jornalMaisRecente = jornais.get(0);
        for (Jornal jornal : jornais ) {
            if (jornal.getDataDePublicacao().after(dataMaisRecente)) {
                dataMaisRecente = jornal.getDataDePublicacao();
                jornalMaisRecente = jornal;
            }
        }
        String dataRetorno = dataBR.format(dataMaisRecente);
        return jornalMaisRecente;
    }

    public ArrayList<Jornal> getJornaisOrdenadosPorData() {

        //Comparator padrão (está na classe Jornal)
        Collections.sort(jornais, Comparator.comparing(Jornal::getDataDePublicacao));
        Collections.reverse(jornais);
        return jornais;
    }

    public ArrayList<Jornal> getJornaisOrdenadosPorLikes() {

        //Comparator customizado. Tá lá no fim do código
        Collections.sort(jornais, ordenaJornais.getLikesComparator());
        Collections.reverse(jornais);
        return jornais;
    }


    public String retorneDataDoJornalMaisBarato() {
        double preco = jornais.get(0).getPreco();
        Date data = jornais.get(0).getDataDePublicacao();
        for (Jornal jornal : jornais) {
            if (jornal.getPreco() < preco) {
                preco = jornal.getPreco();
                data = jornal.getDataDePublicacao();
            }
        }
        String dataRetorno = dataBR.format(data);
        return dataRetorno.substring(0, 1).toUpperCase() + dataRetorno.substring(1);
    }

    public Jornal retornaJornalComMaiorSomaDoTituloESubtitulo() {
        int somaTituloESubtitulo = 0;
        int maiorSomaAteOMomento = 0;
        Jornal maiorJornal = jornais.get(0);
        for (Jornal jornal : jornais) {
            somaTituloESubtitulo = jornal.getSubtitulo().length() +
                    jornal.getTitulo().length();
            if(somaTituloESubtitulo > maiorSomaAteOMomento) {
                maiorSomaAteOMomento = somaTituloESubtitulo;
                maiorJornal = jornal;
            }
        }
        return maiorJornal;
        //Subtitulo no 1 esta escrito errado na API, então o método vai quebrar
    }



    public ArrayList<Jornal> getJornaisDoPaisComMaisJornais() {
        ArrayList<Jornal> jornaisDoPaisComMaisJornais = new ArrayList<Jornal>();

        //Esse hashmap vai pegar as línguas como chave e o contador como valor
        Map<String, Integer> wordMap = new HashMap<String, Integer>();

        //Para cada jornal...
        for (Jornal jornal : jornais) {

            //Tenta colocar a língua como chave
            String input = jornal.getLingua();

            //Se ela já está no mapa, o contador recebe +1
            if (wordMap.get(input) != null) {
                Integer count = wordMap.get(input) + 1;
                wordMap.put(input, count);

                //Senão, coloca ela como chave, e inicia o contador
            } else {
                wordMap.put(input, 1);
            }
        }

        //Compara o valor dos contadores, e devolve a chave (língua) do maior
        Object maxEntry = Collections.max(wordMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        //adiciona os jornais dessa língua numa nova lista, que foi criada lá em cima
        for (Jornal jornal : jornais) {
            if(jornal.getLingua().equals(maxEntry)) {
                jornaisDoPaisComMaisJornais.add(jornal);
            }
        }
        return jornaisDoPaisComMaisJornais;
    }

      public String retornaDataDoJornalMaisCaroNaoEscritoEmPortugues() {
          int maiorpreco = 0;
          Date dataMaisCaro = jornais.get(0).getDataDePublicacao();
          ArrayList<Jornal> jornaisEmOutrasLinguas = new ArrayList<Jornal>();
          for (Jornal jornal : jornais) {
              if (!jornal.getLingua().equals("Português")) {
                  jornaisEmOutrasLinguas.add(jornal);
              }
          }
          for (Jornal jornal : jornaisEmOutrasLinguas) {
              if (jornal.getPreco() > maiorpreco) {
                  dataMaisCaro = jornal.getDataDePublicacao();
              }
          }
          String dataRetorno = dataBR.format(dataMaisCaro);
          return dataRetorno.substring(0, 1).toUpperCase() + dataRetorno.substring(1);
    }

}

class ordenaJornais {
    static Comparator<Jornal> getLikesComparator() {
        return new Comparator<Jornal>() {
            @Override
            public int compare(Jornal o1, Jornal o2) {
                return o1.getLikes() - o2.getLikes();
            }
        };
    }

    }
