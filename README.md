# PetShopSpringBoot


Dono 
* Nome
* Sobrenome
* Cpf
* Telefone
* email
* cidade (///) (Enum)
* endereço  (///)

Veterinário
* ID_Veterinario
* Nome
* Sobrenome
* Cpf
* Telefone
* email
* salário (///)
* especialidade (///)  (Enum)


OS QUE REPETEM:
* Nome
* Sobrenome
* Cpf
* Telefone
* email
  * senha


Consultas
* id_consulta
* id_animal
* id_veterinario
* data_consulta
* motivo
* status (Enum)

Vacinas
* id_vacina
* nome
* descricao
* duracao

Animais
* id_animal
* nome
* especie
* raca
* data_nascimento
* id_dono
* sexo  (Enum)
* peso


RELACIONAMENTOS:<br>

1 dono pode ter N animais <br>
1 animal tem só 1 dono <br>
1 animal N consultas  <br>
1 veterinario N consultas  <br>
1 consulta 1 vatarinario  <br>
1 consulta 1:N vacinas  <br>


