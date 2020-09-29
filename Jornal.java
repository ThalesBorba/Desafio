import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Jornal implements Comparable<Jornal> {

    @SerializedName("nome_do_jornal")
    private String nomeDoJornal;
    private String titulo;
    private String origem;
    private String subtitulo;
    private String lingua;
    private Date data_de_publicacao;
    private int likes;
    private int unlikes;
    private double preco;

    public String getTitulo() {
        return titulo;
    }

    public String getNomeDoJornal() {
        return nomeDoJornal;
    }

    public String getOrigem() {
        return origem;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public String getLingua() {
        return lingua;
    }

    public Date getData_de_publicacao() {
        return data_de_publicacao;
    }

    public int getLikes() {
        return likes;
    }

    public int getUnlikes() {
        return unlikes;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public int compareTo(Jornal outroJornal) {
        return this.getData_de_publicacao().compareTo(outroJornal.data_de_publicacao);
    }
}