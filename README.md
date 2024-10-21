# Previsão do Tempo - Aplicativo Android 🌦️

Este projeto é um aplicativo Android desenvolvido para exibir a previsão do tempo de diferentes cidades, utilizando a API **HG Brasil**. O aplicativo também inclui a funcionalidade de leitura de **QR Code**, permitindo que o nome da cidade seja inserido automaticamente após a leitura.

## Funcionalidades 📱

- **Previsão do tempo:** Busca a previsão do tempo em tempo real de cidades usando a API da HG Brasil.
- **Leitor de QR Code:** Permite escanear um QR Code para preencher o nome da cidade.
- **Interface amigável:** Layout com `TabLayout` para navegação entre abas ("Previsão" e "Sobre").
- **RecyclerView:** Exibe a lista de previsões com temperatura e descrição do clima.
- **API REST:** Integração com a API da HG Brasil para obter dados climáticos.

## Tecnologias Utilizadas 🛠️

- **Linguagem:** Java
- **IDE:** Android Studio
- **APIs:** HG Brasil, JourneyApps QR Code Scanner
- **Componentes:** RecyclerView, FloatingActionButton, ConstraintLayout, TabLayout

## Como Rodar o Projeto 🚀

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio

2. **Abra o projeto no Android Studio.**

3. **Configuração da API HG Brasil:**
   - Crie uma conta em [HG Brasil](https://console.hgbrasil.com/).
   - Obtenha uma chave de API gratuita.
   - Substitua `"SUA_API_KEY_AQUI"` no código por sua chave de API.

4. **Executando o aplicativo:**
   - Conecte um dispositivo Android ou utilize o emulador.
   - Clique em **Run** no Android Studio.

## Estrutura do Projeto 📂

```
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/example/appunipar
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── WeatherFragment.java
│   │   │   │   ├── WeatherAdapter.java
│   │   │   │   └── WeatherData.java
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   └── fragment_weather.xml
│   │   │   │   └── drawable
│   │   │   │       └── ic_qr_code.xml
│   │   │   └── AndroidManifest.xml
```

## Como Utilizar o QR Code Scanner 🎯

1. Clique no **botão de QR Code** no canto inferior direito.
2. Escaneie um QR Code contendo o nome de uma cidade.
3. O nome da cidade será preenchido automaticamente no campo de busca.
4. Clique em **Buscar** para visualizar a previsão do tempo.

## Exemplo de Uso 🖼️

| Tela Inicial | Leitor QR Code |
|--------------|----------------|
| ![Tela Inicial](link-para-imagem-1) | ![Leitor QR](link-para-imagem-2) |

## Código de Integração com a API HG Brasil 🌐

Aqui está um trecho de exemplo do código usado para obter dados da API HG Brasil:

```java
private void getWeatherData(String cidade) {
    new Thread(() -> {
        try {
            String apiKey = "SUA_API_KEY_AQUI";
            String urlString = "https://api.hgbrasil.com/weather?format=json-cors&key=" + apiKey + "&city_name=" + cidade;

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject results = jsonObject.getJSONObject("results");
            double temperature = results.getDouble("temp");
            String description = results.getString("description");

            weatherList.add(new WeatherData(cidade, temperature, description));
            getActivity().runOnUiThread(() -> weatherAdapter.notifyDataSetChanged());

        } catch (Exception e) {
            e.printStackTrace();
            getActivity().runOnUiThread(() -> {
                weatherList.add(new WeatherData(cidade, 0.0, "Erro ao buscar previsão."));
                weatherAdapter.notifyDataSetChanged();
            });
        }
    }).start();
}
```

## Contribuição 🤝

1. Faça um fork do projeto.
2. Crie uma branch: `git checkout -b minha-branch`.
3. Faça suas alterações e commit: `git commit -m 'Minhas alterações'`.
4. Envie para o repositório remoto: `git push origin minha-branch`.
5. Abra um Pull Request.

## Autor 👤

Desenvolvido por **Marcos Schumacher** - Entre em contato:
- https://www.linkedin.com/in/marcos-schumacher-0a300010a/
- Email: marcosrschumacher@gmail.com
```
