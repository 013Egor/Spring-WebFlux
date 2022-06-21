# Spring-WebFlux
## Неупорядоченная выдача результатов
№ итерации, имя функции, результат функции, время выполнения


## Упорядоченная выдача результатов
№ итерации, имя функции №1, результат функции, время выполнения, количество полученный наперед результатов функции №1, имя функции №2, результат функции, время выполнения, количество полученный наперед результатов функции №2

## Тестовый клиент (неупорядоченная выдача результатов)
http://localhost:8080/calculate?code1=function%20code1%20%28a%29%7B%20function%20sleep%28milliseconds%29%20%7B%20var%20start%20%3D%20new%20Date%28%29.getTime%28%29%3B%20for%20%28var%20i%20%3D%200%3B%20i%20%3C%201e7%3B%20i%2B%2B%29%20%7Bif%20%28%28new%20Date%28%29.getTime%28%29%20-%20start%29%20%3E%20milliseconds%29%7Bbreak%3B%7D%7D%7D%3B%20sleep%288000%29%3B%20return%20a%3B%7D&code2=function%20code1%20%28a%29%7B%20function%20sleep%28milliseconds%29%20%7B%20var%20start%20%3D%20new%20Date%28%29.getTime%28%29%3B%20for%20%28var%20i%20%3D%200%3B%20i%20%3C%201e7%3B%20i%2B%2B%29%20%7Bif%20%28%28new%20Date%28%29.getTime%28%29%20-%20start%29%20%3E%20milliseconds%29%7Bbreak%3B%7D%7D%7D%3B%20sleep%281000%29%3B%20return%20a%3B%7D&amount=10&how=0

## Тестовый клиент (упорядоченная выдача результатов)
http://localhost:8080/calculate?code1=function%20code1%20%28a%29%7B%20function%20sleep%28milliseconds%29%20%7B%20var%20start%20%3D%20new%20Date%28%29.getTime%28%29%3B%20for%20%28var%20i%20%3D%200%3B%20i%20%3C%201e7%3B%20i%2B%2B%29%20%7Bif%20%28%28new%20Date%28%29.getTime%28%29%20-%20start%29%20%3E%20milliseconds%29%7Bbreak%3B%7D%7D%7D%3B%20sleep%288000%29%3B%20return%20a%3B%7D&code2=function%20code1%20%28a%29%7B%20function%20sleep%28milliseconds%29%20%7B%20var%20start%20%3D%20new%20Date%28%29.getTime%28%29%3B%20for%20%28var%20i%20%3D%200%3B%20i%20%3C%201e7%3B%20i%2B%2B%29%20%7Bif%20%28%28new%20Date%28%29.getTime%28%29%20-%20start%29%20%3E%20milliseconds%29%7Bbreak%3B%7D%7D%7D%3B%20sleep%281000%29%3B%20return%20a%3B%7D&amount=10&how=1

## Функции, передаваемые в первом и втором клиенте

function code1 (a){ function sleep(milliseconds) { var start = new Date().getTime(); for (var i = 0; i < 1e7; i++) {if ((new Date().getTime() - start) > milliseconds){break;}}}; sleep(8000); return a;}

function code1 (a){ function sleep(milliseconds) { var start = new Date().getTime(); for (var i = 0; i < 1e7; i++) {if ((new Date().getTime() - start) > milliseconds){break;}}}; sleep(1000); return a;}
