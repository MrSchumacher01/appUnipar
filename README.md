
# Previsão do Tempo - Aplicativo Android 🌤️

Este projeto é um aplicativo Android desenvolvido para exibir a previsão do tempo de diferentes cidades, utilizando a API **OpenWeatherMap**. O aplicativo também inclui a funcionalidade de leitura de **QR Code**, permitindo que o nome da cidade seja inserido automaticamente após a leitura.

## Funcionalidades 📱

- **Previsão do tempo:** Busca a previsão do tempo em tempo real de cidades usando a API do OpenWeatherMap.
- **Leitor de QR Code:** Permite escanear um QR Code para preencher o nome da cidade.
- **Interface amigável:** Layout com `TabLayout` para navegação entre abas ("Previsão" e "Sobre").
- **RecyclerView:** Exibe a lista de previsões com temperatura e descrição do clima.
- **API REST:** Integração com a API OpenWeatherMap.

## Tecnologias Utilizadas 🛠️

- **Linguagem:** Java
- **IDE:** Android Studio
- **APIs:** OpenWeatherMap, JourneyApps QR Code Scanner
- **Componentes:** RecyclerView, FloatingActionButton, ConstraintLayout, TabLayout

## Como Rodar o Projeto 🚀

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Abra o projeto no Android Studio.**

3. **Configuração da API OpenWeatherMap:**
   - Crie uma conta no [OpenWeatherMap](https://openweathermap.org/).
   - Gere uma chave de API gratuita.
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

## Contribuição 🤝

1. Faça um fork do projeto.
2. Crie uma branch: `git checkout -b minha-branch`.
3. Faça suas alterações e commit: `git commit -m 'Minhas alterações'`.
4. Envie para o repositório remoto: `git push origin minha-branch`.
5. Abra um Pull Request.

## Autor 👤

Desenvolvido por **Marcos Schumacher** - Entre em contato:
- [LinkedIn][(https://github.com/MrSchumacher01)]
- Email: marcosrschumacher@gmail.com

