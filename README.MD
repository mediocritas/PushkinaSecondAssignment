# Инструкция по запуску тестов 
## 1. Установка Java Development Kit (JDK)

Убедитесь, что у вас установлена Java версии 11. Вы можете загрузить и установить JDK с [официального сайта Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

## 2. Получение проекта

Склонируйте проект из репозитория или загрузите его архив.

```bash
git clone https://github.com/mediocritas/PushkinaSecondAssignment.git
```
## 3. Переход в директорию проекта
   Перейдите в директорию проекта с помощью команды cd.
   Например
```bash
cd ~/IdeaProjects/PushkinaSecondAssigment
```

## 4. Запуск тестов с помощью Gradle Wrapper
Выполните следующую команду для запуска тестов с помощью chromium:

```bash
./gradlew test
```

Чтобы выбрать другой браузер для запуска и параметры окна браузера выполните

```bash
./gradlew test -Db<rowserType>=firefox -Dwidth=1920 -Dheight=1080
```
Доступные браузеры для выполнения: chromium, FireFox, Webkit

## 5. Просмотр результатов тестов
Результаты тестов будут доступны в консоли после выполнения команды ./gradlew test. 
Кроме того, вы можете найти дополнительную информацию о результатах тестирования в файлах отчетов в папке build/reports/tests.
Скриншоты будут находиться в папке output.