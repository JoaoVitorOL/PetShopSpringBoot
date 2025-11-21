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


RELACIONAMENTOS:

1 dono pode ter N animais
1 animal tem só 1 dono
1 animal N consultas
1 veterinario N consultas
1 consulta 1 vatarinario
1 consulta 1:N vacinas



