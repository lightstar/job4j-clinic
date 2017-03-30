# Клиника домашних питомцев

Мой вольный вариант программы **"Клиника домашних питомцев"** из курса Петра Арсентьева **"Java. Путь от ученика до эксперта"**.

Ссылка на сам курс: http://job4j.ru/courses/java_way_from_student_to_master.html

Для запуска (с учетом установленных JDK и maven) нужно ввести в директории проекта команду: `mvn exec:java`

Суть этой простой консольной программы в следующем. Имеется клиника домашних животных на фиксированное количество
позиций, каждую из которых может занимать некоторый клиент с питомцем, и с ними можно производить различные действия:
- Добавить клиента в клинику.
- Указать тип и имя питомца, который есть у клиента.
- Изменить имя клиента или имя питомца.
- Удалить питомца у клиента или удалить самого клиента.
- Найти клиента по имени.
- Найти всех клиентов по имени питомца.
- Попросить питомца издать звук.

Имя клиента должно быть уникальным. Также на данном этапе все данные хранятся в памяти и теряются после выхода из
программы.

Взаимодействие с программой происходит следующим образом. Отображается текстовое меню со списком всех возможных
действий. Чтобы выполнить действие, необходимо ввести в консоль код команды, указанный в скобках, после чего, возможно,
у пользователя будут запрошены дополнительные данные, которые также нужно будет ввести в консоль. Для выхода из
программы используется команда `exit`.
