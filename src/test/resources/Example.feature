Feature: Проверка сайта sql-ex.ru
  Scenario Outline: Написать функцию записи куков в файл и считывания куков из него.
    Given Открываем страницу сайта sql-ex.ru
    And Ввести в поле "Логин" свой логин
    And Ввести в поле "Пароль" свой пароль
    And Нажать кнопку входа "Вход"
    And Записать Cookies в файл
    And Выйти из профиля удалив Cookies
    And Прочитать Cookies из файла
    Then Фиксируем, что Nick Name найден
    Examples:
      | email     | password  | nick_name |
      |reebook173 |Snoopy1234 | Jerar173  |