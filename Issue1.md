# Описание
Нет сообщения об ошибке при вводе Владельца латинецей и/или цифрами
# Локация дефекта
https://github.com/Stepan-Prosin/gradletest8/blob/1e4cb192cb59f2542579ce6ba6eb494cc56c127d/src/test/java/ru/netology/web/MoneyTransferTest.java#L50
https://github.com/Stepan-Prosin/gradletest8/actions/runs/7645319926/job/20831609818#step:6:444

# Шаги Воспроизведения
1. Запуск приложения  java -jar aqa-shop.jar
2. Вход на сайт по локальному подключению с URL http://localhost:8080/
3. Выбрать одну из карт для оплаты. 
4. Ввод валидных карты ,месяца и года.
5.Ввод кода верификации. .
6.  Ввод владельца латиницей или цифрами
7. Посмотреть результат 

Ожидаемый результат: символы не ввелись и появилось сообщение "Поле обязательно для заполнения"
Фактический результат: тест падает и оплата поисходит успешно

# Скриншот
![image](https://github.com/Stepan-Prosin/gradletest8/assets/57405022/158c7ff6-577b-467c-8fb5-527321f95948)

# Окружение 
* **Операционная система^**  Windows 10 Pro
* **IDE:** IntelliJ IDEA 2023.2.3 (Community Edition)
* **Java:** OpenJDK 11