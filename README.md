# Java Web Application com Integração de APIs (Steam, YouTube e IBGE)

## Descrição
Aplicação web desenvolvida em Java (Servlets e JSP) que integra APIs externas como Steam, YouTube e IBGE, permitindo busca de perfis, canais e informações de estados.

## Funcionalidades

### Steam
- Busca perfil de usuário pelo Steam ID  
- Exibe:
  - Nome do usuário  
  - Link do perfil  
  - Status  

### YouTube
- Busca canais com base em uma palavra-chave  
- Exibe:
  - Nome do canal  
  - Link direto para o canal  

Suporte a caracteres especiais com uso de URLEncoder.

### IBGE
- Seleção de estados (SP, RJ, MG)  
- Exibição de:
  - Capital  
  - População  
  - Área  

## Tecnologias utilizadas
- Java (Servlets)
- JSP
- HTML
- APIs REST
- JSON (json-simple)

## Estrutura do Projeto
