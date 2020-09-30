import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Jornal implements Comparable<Jornal> {

    @SerializedName("nome_do_jornal")
    private String nomeDoJornal;
    private String titulo;
    private String origem;
    private String subtitulo;
    private String lingua;
    @SerializedName("data_de_publicacao")
    private Date dataDePublicacao;
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

    public Date getDataDePublicacao() {
        return dataDePublicacao;
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
        return this.getDataDePublicacao().compareTo(outroJornal.dataDePublicacao);
    }

    @Override
    public String toString() {
        return nomeDoJornal;
    }
}