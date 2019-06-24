package com.obruno.discos.service;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Serviço com as regras para criação de discos a partir da Api do Spotify.
 */

import com.obruno.discos.exception.DiscoCreationFromSpotifyApiException;
import com.obruno.discos.exception.SpotifyInvalidToken;
import com.obruno.discos.model.ArtistaBanda;
import com.obruno.discos.model.Disco;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class SpotifyWebApiServiceImpl implements SpotifyWebApiService {
    private final List<String> generos = Arrays.asList("pop", "mpb", "classic", "rock");

    @Autowired
    private DiscoService discoService;

    @Override
    public void criarDiscosSpotify(final String token) {
        log.info("Creating discos...");
        final String tokenAuth = String.format("Bearer %s", token);
        generos.forEach(genero -> criarDiscosSpotify(genero, tokenAuth));
        log.info("Discos created");
    }

    private void criarDiscosSpotify(final String genero, final String token)  {
        try {
            final URL obj = new URL(String.format("https://api.spotify.com/v1/recommendations?limit=50&seed_genres=%s", genero));
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", token);

            JSONObject json = lerJson(con.getInputStream());
            processarRetornoJson(json, genero);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new SpotifyInvalidToken();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new DiscoCreationFromSpotifyApiException(e.getLocalizedMessage());
        }
    }

    private void processarRetornoJson(JSONObject json, String genero) {
        List<Disco> discos = new ArrayList<>();
        try {
            JSONArray tracks = json.getJSONArray("tracks");

            for (int i = 0; i < tracks.length(); i++) {
                String artistName = tracks.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name");
                String albumName = tracks.getJSONObject(i).getString("name");
                discos.add(Disco.builder()
                        .genero(genero)
                        .album(albumName)
                        .artistaBanda(ArtistaBanda.builder()
                                .nome(artistName)
                                .build())
                        .valorDisco(criarValorDoDisco())
                        .build());
            }
            discoService.salvarDiscos(discos);
        } catch (JSONException e) {
            log.error(e.getLocalizedMessage());
            throw new DiscoCreationFromSpotifyApiException(e.getLocalizedMessage());
        }

    }

    private BigDecimal criarValorDoDisco() {
        BigDecimal max = new BigDecimal(100);
        BigDecimal randFromDouble = BigDecimal.valueOf(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec.setScale(2, BigDecimal.ROUND_DOWN);
        return actualRandomDec;
    }

    private static String lerTudo(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject lerJson(InputStream inputStream) throws IOException {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = lerTudo(rd);
            return new JSONObject(jsonText);
        } catch (Exception e) {
            inputStream.close();
            log.error(e.getLocalizedMessage());
        }

        inputStream.close();
        return null;
    }

}