# artConnect (Back-End)
## Dados do projeto
- Início de desenvolvimento: 19/02/2026
- Linguagem: Java 21.
- Framework e libs: Spring Boot, Lombok Spring JPA
## Regras de Desenvolvimento
- Antes de começar a programar, use <code>git pull origin &lt;nome-da-branch&gt; </code> para garantir o versionamento.
- Não fazer commits diretamente na branch <code>main</code>.
- **Dica:** Configure o lombok na sua IDE
- Usar camelCase.
- exceto nas controllers e rotas, usar nome de variáveis em português.
- Identação no código.
- funções e variáveis com nomes claros e se possível comente.

## Divisão de Branchs
- <code>entity</code> Implementação das entidades previstas no Diagrama de Classes.
- <code>controller</code> Desenvolvimento das rotas e endpoints.
- <code>service</code> Desenvolvimento das regras de negócio e intermédio entre controller e repository.
- <code>repository</code> Integração com o banco de dados.
- <code>testes</code> Realização de Testes unitários e de integração.
- <code>dto</code> Classes que fazem intermédio entre a API e o banco de dados.
## Imagens de apoio
### Diagrama de Classes
<img width="1644" height="1388" alt="ClassesBack" src="https://github.com/user-attachments/assets/ef862f94-9b77-4605-b956-087be0e9b8d3" />

### Rascunho do Back-End
<img width="6296" height="2170" alt="Image" src="https://github.com/user-attachments/assets/d4d6e6f4-388e-4b7d-b67c-baa24ffdb2f1" />
