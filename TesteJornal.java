import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TesteJornal{
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "https://private-a308c-desafiojava.apiary-mock.com/jornais ";
        ResponseEntity<String> response = restTemplate.getForEntity(apiURL, String.class);

        Gson gson = new Gson();
        JornalResponse jornalResponse = gson.fromJson(response.getBody(), JornalResponse.class);
        ArrayList<Jornal> listaDeJornais = jornalResponse.getJornais();

        ResumoJornal resumoJornal = new ResumoJornal(listaDeJornais);

        System.out.println(resumoJornal.quantidadeDeJornaisDaLista());


    }
}