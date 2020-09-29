import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ResumoJornal extends JornalResponse {

    private ArrayList<Jornal> jornais;

    ResumoJornal(ArrayList<Jornal> jornais) {
        this.jornais = jornais;
    }

    Locale localeBR = new Locale("pt", "BR");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
    DateFormat dataBR = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' y");

    public int quantidadeDeJornaisDaLista() {
        return jornais.size();
    }

    public String getJornalMaisCaro() {
        double maiorPreco = 0;
        String jornalCorrespondente = "";
        for (Jornal jornal : jornais) {
            if (jornal.getPreco() > maiorPreco) {
                maiorPreco = jornal.getPreco();
                jornalCorrespondente = jornal.getNomeDoJornal();
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
        Date dataMaisAntiga = jornais.get(0).getData_de_publicacao();
        for (Jornal jornal : jornais ) {
            if (jornal.getData_de_publicacao().before(dataMaisAntiga)) {
                dataMaisAntiga = jornal.getData_de_publicacao();
            }
        }
        String dataRetorno = dataBR.format(dataMaisAntiga);
        return dataRetorno.substring(0, 1).toUpperCase() + dataRetorno.substring(1);
    }

    public String getJornalMaisRecente() {
        Date dataMaisRecente = jornais.get(0).getData_de_publicacao();
        for (Jornal jornal : jornais ) {
            if (jornal.getData_de_publicacao().after(dataMaisRecente)) {
                dataMaisRecente = jornal.getData_de_publicacao();
            }
        }
        String dataRetorno = dataBR.format(dataMaisRecente);
        return dataRetorno.substring(0, 1).toUpperCase() + dataRetorno.substring(1);
    }

    public ArrayList<Jornal> getJornaisOrdenadosPorData() {
        Collections.sort(jornais, Comparator.comparing(Jornal::getData_de_publicacao));
        return jornais;
    }

    public ArrayList<Jornal> getJornaisOrdenadosPorLikes() {
        // TODO() Retorne uma  lista de jornais ordenado com base na quantidade de likes (Do jornal com mais likes até o com menos likes)
        Collections.sort(jornais, ordenaJornais.getLikesComparator());
        return jornais;
        //Porque está retornando pointers?
    }


    public String retorneDataDoJornalMaisBarato() {
        double preco = jornais.get(0).getPreco();
        Date data = jornais.get(0).getData_de_publicacao();
        for (Jornal jornal : jornais) {
            if (jornal.getPreco() < preco) {
                preco = jornal.getPreco();
                data = jornal.getData_de_publicacao();
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
        //Subtitulo no 1 esta escrito errado
    }



    public ArrayList<Jornal> getJornaisDoPaisComMaisJornais() {
        //TODO() - Retorne a lista de jornais do país com mais jornais
        ArrayList<Jornal> jornaisDoPaisComMaisJornais = new ArrayList<Jornal>();
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for (Jornal jornal : jornais) {
            String input = jornal.getLingua();
            if (wordMap.get(input) != null) {
                Integer count = wordMap.get(input) + 1;
                wordMap.put(input, count);
            } else {
                wordMap.put(input, 1);
            }
        }
        Object maxEntry = Collections.max(wordMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (Jornal jornal : jornais) {
            if(jornal.getLingua().equals(maxEntry)) {
                jornaisDoPaisComMaisJornais.add(jornal);
            }
        }
        return jornaisDoPaisComMaisJornais;
    }

      public String retornaDataDoJornalMaisCaroNaoEscritoEmPortugues() {
          int maiorpreco = 0;
          Date dataMaisCaro = jornais.get(0).getData_de_publicacao();
          ArrayList<Jornal> jornaisEmOutrasLinguas = new ArrayList<Jornal>();
          for (Jornal jornal : jornais) {
              if (!jornal.getLingua().equals("Português")) {
                  jornaisEmOutrasLinguas.add(jornal);
              }
          }
          for (Jornal jornal : jornaisEmOutrasLinguas) {
              if (jornal.getPreco() > maiorpreco) {
                  dataMaisCaro = jornal.getData_de_publicacao();
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
