## **API DOCUMENTAÇÃO - CADASTRO DE PROPRIEDADE**

API elaborada para cadastrar propriedades, calcular metragem e valor das mesmas.


**FUNCIONALIDADES:**

* Cadastrar Propriedade
* Calcular o valor de uma propriedade
* Calcular a área total de uma propriedade
* Calcular o maior cômodo de uma propriedade
* Determinar a área de cada cômodo de uma propriedade



## **Parâmetros de Propriedade:**

**Cadastrar Propriedade:**

O cadastro da propriedade é recebido via JSON, convertido para objeto JAVA e então armazenado no banco de dados H2

**Endpoint** 

http://localhost:8080/api/v1/ground

**Modelo de payload**

```
{
    "propName": "Apartamento",
    "district": {
        "propDistrict": "Centro",
        "valueDistrictM2": 10.0
    },
    "rooms": [
        {
            "roomName": "Quarto",
            "roomLength": 3,
            "roomWidth": 3
        },
        {
            "roomName": "Sala",
            "roomLength": 5,
            "roomWidth": 5
        },
        {
            "roomName": "Cozinha",
            "roomLength": 4,
            "roomWidth": 4
        }
    ]
}
```
*Será lançado exceção no sistema em caso de:*

* Dimensões dos cômodos negativos;

* Campos nulos/vazios;

* Valor do metro quadrado do distrito ser negativo;

* Valor do comprimento do cômodo ultrapassar 30 metros;

* Valor da largura do cômodo ultrapassar 25 metros;

* Nome do bairro ultrapassar 45 caracteres;

* Nome da propriedade ultrapassar 30 caracteres;

* O nome da propriedade, bairro ou cômodo começarem com letra minúscula.





**Calcular o valor de uma propriedade:**

O cálculo do valor de uma propriedade é feito por um método que busca a propriedade no banco de dados através do seu ID e multiplica o metro quadrado da propriedade pelo valor do metro quadrado do bairro;

Para efetuar o cálculo do valor de uma propriedade utilizamos o método HTTP **GET** através do seguinte link:

**Endpoint**

http://localhost:8080/api/v1/ground/{ID_PRORPIEDADE}/value

**Modelo de resposta**

```
500.00
```

*A consulta retorna uma String com o valor calculado*




**Calcular a área total de uma propriedade:**

O cálculo da área total de uma propriedade é feito por um método que busca a propriedade no banco de dados através do seu ID e multiplica a largura pelo comprimento de cada cômodo e faz a somatória deles, retornando a área total da propriedade;

Para efetuar o cálculo da área total de uma propriedade utilizamos o método HTTP **GET** através do seguinte link:

**Endpoint**

http://localhost:8080/api/v1/ground/{ID_PRORPIEDADE}/area

**Modelo de resposta**

```
50.00
```

*A consulta retorna uma String com o valor calculado*





**Calcular o maior cômodo de uma propriedade:**

O cálculo do maior cômodo de uma propriedade é feito por um método que busca a propriedade no banco de dados através do seu ID e percorre a lista de cômodos da mesma, fazendo o calculo do metro quadrado de cada cômodo, para então retornar o maior da lista;

Para efetuar o cálculo do maior cômodo de uma propriedade utilizamos o método HTTP **GET** através do seguinte link:

**Endpoint**

http://localhost:8080/api/v1/ground/{ID_PRORPIEDADE}/room/biggest

**Modelo de resposta**

```
{
    "name": "Sala",
    "area": 25.0
}
```

*A consulta retorna um JSON com o nome do cômodo e o valor da sua área*




**Determinar a área de cada cômodo de uma propriedade:**

O cálculo de todas as áreas da propriedade é feito por um método que busca a propriedade no banco de dados através do seu ID e percorre a lista de cômodos da mesma, fazendo o calculo do metro quadrado de cada cômodo, para então retornar uma lista de cômodos;

Para efetuar o cálculo de todas as áreas da propriedade utilizamos o método HTTP **GET** através do seguinte link:

**Endpoint**

http://localhost:8080/api/v1/ground/{ID_PRORPIEDADE}/room/areas

**Modelo de resposta**

```
[
    {
        "name": "Quarto",
        "area": 9.0
    },
    {
        "name": "Sala",
        "area": 25.0
    },
    {
        "name": "Cozinha",
        "area": 16.0
    }
]
```

*A consulta retorna um JSON com uma lista de cômodos e o valor da suas áreas*
