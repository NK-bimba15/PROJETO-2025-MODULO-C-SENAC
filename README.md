# Pong Game com Sistema de Ranking 🕹️🏆

Projeto simples feito em **Java**, utilizando **Swing** para a interface gráfica. O jogo é uma versão clássica do **Pong**, com sistema de pontuação e **ranking persistente** em banco de dados.

## 🎮 Objetivo

Controlar uma barra (paddle) e rebater a bolinha o maior tempo possível. A cada rebote, sua pontuação aumenta. Quando a bolinha passa da sua barra, o jogo termina e sua pontuação é salva no ranking.

---

## 🧠 Funcionalidades

-  Jogo Pong funcional
-  Interface gráfica com Java Swing
-  Sistema de pontuação automática
-  Tela de menu principal
-  Ranking persistente com score
-  Armazenamento em banco de dados

---

## 📦 Estrutura do Projeto

| Arquivo              | Descrição |
|----------------------|-----------|
| `Main.java`          | Classe principal (inicializa o programa) |
| `Menu.java`          | Interface inicial do jogo (menu e botões) |
| `Menu.form`          | Arquivo de layout do NetBeans (Swing Designer) |
| `PongGame.java`      | Lógica e renderização do jogo Pong |
| `Ranking.java`       | Tela de exibição dos melhores jogadores |
| `PontuacaoDAO.java`  | Classe de acesso ao banco de dados (DAO para pontuações) |

---

## 🖥️ Tecnologias Utilizadas

-  Java JDK 8+
-  Swing (Interface Gráfica)
-  JDBC (para persistência dos dados)
-  NetBeans (opcional, para edição visual)

---

## 🔧 Como Executar

-  Importe o projeto no NetBeans.
-  Certifique-se de que o driver JDBC está disponível.
-  Compile e execute a classe Main.java.

---


## 🏅 Ranking
A pontuação é salva automaticamente ao fim de cada partida. Os melhores scores são exibidos na tela de ranking.

---

## 🙌 Créditos
Desenvolvido por Nick K.
Com inspiração no documento do Jogo da Forca por Luís Guilherme de S Lopes.

---

## 📜 Licença
Este projeto está sob a supervisão de professor Luís Guilherme de S Lopes, institudo SENAC 2025.
