Avaliação básica:

Nesta avaliação, faremos um pequeno projeto de transferência de dados contidos em arquivos para um banco de dados, com geração de relatórios.
Ele foi dividido em partes para melhor entendimento do projeto.

## Primeira Parte

O arquivo pode ser um txt, e deve estar neste formato. Basicamente copie o conteúdo abaixo e cole num txt.

```
Nome            | Idade | Município onde mora | Estado 
Antonio Carlos  | 43    | São Tomé das Letras | MG
Cláudia Pereira | 20    | Aparecida           | SP    
José dos Santos | 30    | Manaus              | AM     
Thomas Bloch    | 22    | Marechal Deodoro    | AL    
Maria Aparecida | 35    | Feira de Santana    | BA    
Diego da Silva  | 29    | Macapá              | AP     
Valentina de Sá | 28    | Uruguaiana          | RS     
Leonardo Souza  | 30    | Araraquara          | SP     
Miguel Pereira  | 33    | Parati              | RJ     
```

Em seguida, crie as classes ``LeitorDeArquivoTest`` e ``LeitorDeArquivo``. Esta última deverá ler o arquivo recém criado e retornar objetos do tipo ``Cidadao``. Cada objeto ``Cidadao`` deve representar uma linha do arquivo (exceto a primeira linha, que é o cabeçalho do arquivo e que, portanto, deve ser ignorada). 
Naturalmente que a primeira classe mencionada é um teste unitário de leitura e criação de objetos ``Cidadao``. Segue abaixo, portanto, algumas sugestões de possíveis cenários de teste:

* ler um arquivo vazio. O ``LeitorDeArquivo`` deverá retornar uma  _collection_  vazia de cidadãos. 
* ler um arquivo que não existe. O ``LeitorDeArquivo`` deverá lançar uma exceção, informando que o arquivo não existe. Essa exceção pode ser uma da biblioteca padrão do Java ou uma criada por você mesmo. 
* ler um arquivo com 1 linha. O ``LeitorDeArquivo`` deverá retornar uma  _collection_  contendo 1 cidadão.
* ler um arquivo com n linhas. O ``LeitorDeArquivo`` deverá retornar uma  _collection_  contendo n cidadãos.

Não é necessário que se escrevam os testes acima nessa ordem. Também sinta-se à vontade para escrever mais testes se julgar necessário. Entretanto, é importante verificar se os objetos do tipo ``Cidadao`` que estão sendo retornados são realmente os que se esperava.

A  _collection_  a ser usada durante todo o processo pode ser escolhida por você. Pode ser ``List``, ``Set`` ou mesmo ``Map``!

## Segunda Parte
Agora que temos um leitor de arquivos funcionando, podemos partir para a persistência dos dados lidos em banco de dados.

Num banco de dados MySql, crie as tabelas *pessoas* e *logradouros*. A tabela *pessoas* deve conter o nome e a idade de cada uma delas. E a tabela *logradouros* o município onde a pessoa mora e o estado. Para facilitar as coisas, podemos nomear as colunas com nomes semelhantes aos usados no cabeçalho do arquivo.

Crie uma classe ``CidadaoDAO``. Esta será responsável por persistir os cidadãos que vierem do arquivo txt. Se possível, escreva uma classe de teste também para essa classe.

Também precisaremos criar 2 métodos em ``CidadaoDAO``:

* ``buscarSomenteOsDaRegiaoSudeste()``, que irá retornar uma  _collection_  contendo cidadãos dos estados SP, RJ, MG e ES. Utilizar a busca em banco de dados (``SELECT``) para retornar essa informação;
* ``buscarSomenteOsMaioresDe30()``, que irá retornar uma  _collection_  contendo cidadãos que possuem 30 anos ou mais. Utilizar o lambda do Java para realizar a seleção (ou seja, faça um ``SELECT`` que retorne todos os cidadãos do banco, com seus respectivos logradouros, e faça a seleção por idade usando o lambda);

## Terceira Parte
Agora que as 2 partes da aplicação foram montadas (leitura de um lado e escrita de outro), vamos então juntá-las.

Para isso, vamos criar uma classe que possua o método ``main`` e dentro dela, vamos fazer o seguinte:

* instanciar o ``LeitorDeArquivo`` e o ``CidadaoDAO``
* ler o arquivo txt, com o ``LeitorDeArquivo``, que deve retornar uma  _collection_  de cidadãos
* persistir todos os cidadãos em banco, usando o ``CidadaoDAO``
* buscar os cidadãos da região sudeste, e imprimi-los na tela (com ``System.out.println()`` mesmo)
* buscar os cidadãos maiores de 30 anos e imprimi-los na tela (com ``System.out.println()`` mesmo)

Dica: na 2 impressões acima, sobrescreva o método ``toString()`` da classe ``Cidadao`` da forma como achar melhor. O importante, nesse caso, é exibir todos os atributos de ``Cidadao``. o ``System.out.println()`` deve estar, entretanto, fora do ``toString()``. 

Ao rodar a aplicação, a saída deverá ser semelhante a abaixo:

```
Cidadãos da região sudeste:
Cidadao [nome=Antonio Carlos, idade=43, municipio=São Tomé das Letras, estado=MG]
Cidadao [nome=Cláudia Pereira, idade=20, municipio=Aparecida, estado=SP]
Cidadao [nome=Leonardo Souza, idade=30, municipio=Araraquara, estado=SP]
Cidadao [nome=Miguel Pereira, idade=33, municipio=Parati, estado=RJ]
Cidadãos maiores de 30:
Cidadao [nome=Antonio Carlos, idade=43, municipio=São Tomé das Letras, estado=MG]
Cidadao [nome=José dos Santos, idade=30, municipio=Manaus, estado=AM]
Cidadao [nome=Maria Aparecida, idade=35, municipio=Feira de Santana, estado=BA]
Cidadao [nome=Leonardo Souza, idade=30, municipio=Araraquara, estado=SP]
Cidadao [nome=Miguel Pereira, idade=33, municipio=Parati, estado=RJ]
```

## Observações

é possível se chegar ao mesmo resultado mesmo seguindo caminhos diferentes. O importante é que os passos descritos acima sejam cumpridos. 
Serão analisados:
* a maneira com que o código foi escrito
* a escrita de testes automatizados
* utilização correta de ``equals()`` e ``hashCode()``
* a utilização de recursos como o try/catch e lambda
* a maneira com que foram lidos os dados do banco, bem como a sua conversão para objetos do tipo ``Cidadao``