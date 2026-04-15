<%-- 
    Document   : index
    Created on : 27 de abr. de 2024, 19:25:05
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Steam Profile Lookup</title>
</head>
<body>
    <h1>Buscar Perfil da Steam</h1>
    <form action="SteamProfileServlet" method="GET">
        <label for="steamId">ID da Steam:</label>
        <input type="text" id="steamId" name="steamId" required>
        <button type="submit">Buscar</button>
    </form>
    <h1>Buscar Canais no YouTube</h1>
    <form action="YoutubeSearchServlet" method="GET">
        <label for="searchQuery">Digite a palavra-chave:</label>
        <input type="text" id="searchQuery" name="searchQuery">
        <input type="submit" value="Pesquisar">
    </form>
    <form action="IBGEDataServlet" method="GET">
        <h1>Selecione um estado:</h1>
        <select name="estado">
            <option value="SP">São Paulo</option>
            <option value="RJ">Rio de Janeiro</option>
            <option value="MG">Minas Gerais</option>
        </select>
        <br><br>
        <input type="submit" value="Mostrar Informações">
    </form>
</body>
</html>
