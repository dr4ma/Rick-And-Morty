# Rick-And-Morty. Краткое описание
Rick-And-Morty - приложение, которое взаимодействует с The Rick and Morty API и получает данные о всех персонажах, которые появлялись в анимационном сериале.
В приложении два экрана: первый отображает всех персонажей, а второй появляется при клике на персонажа из первого экрана, и показывает детальную информацию о конкретном персонаже.
В приложении имеется: пагинация запросов при достижении конца актуального списка, кэширование данных в локальную БД, проверка на соединение с интернетом.
Как только интернет пропадает на устройстве, то приложение переходит в режим оффлайн. Все данные приложение запрашивает из локальной БД и отображает их пользователю.
Если интернет появился, то приложение отправляет запросы уже в API, а не в локальнуб БД. При скачивании данных с API данные записываются и в локальную БД.
# Rick-And-Morty. Стек технологий
Иcпользуемый стек технологий: Kotlin, MVVM + LiveData, Clean architecture, Retrofit, Room, Dagger Hilt, Navigation Component, Picasso
# Rick-And-Morty. Скрины приожения
![Screenshot_2](https://user-images.githubusercontent.com/94142972/193041087-b6deaefa-6805-4682-9f5c-e20886ad5a09.png)
![Screenshot_28](https://user-images.githubusercontent.com/94142972/193041093-ae735671-c287-461b-8518-a9cb32b34376.png)
![Screenshot_1](https://user-images.githubusercontent.com/94142972/193041095-82e459ce-142d-4e48-bee5-6cb599fffaf4.png)
