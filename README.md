# Authorship-Analyzer
A simple software solution that provides a textual in-depth and detects potential plagiarism

## Какво е Authorship Analyzer?
Authorship detection е процесът на алгоритмично идентифициране на автора на даден анонимен текст. Основната идея е извличане на различни статистики от текста (т.нар. feature-и в контекста на Machine Learning) с цел формиране на лингвистичен подпис (linguistic signature) за всеки текст.

Примери за feature-и са например средният брой думи в изречение или пък средната дължина на думите.

Имайки лингвистичните подписи на два текста (т.е. поредици от числа, всяко от които съответства на стойността на даден feature), можем да определим сходството между тях и да изчислим каква е вероятността да са написани от един и същ автор.

Автоматизираният authorship detection днес е област на активен изследователски интерес и има приложения в plagiarism detection, филтрирането на имейли, проучвания в социалните науки и дори в съдебната практика като доказателство в дела. Конкретната задача е вдъхновена от курс по програмиране във [Факултета по компютърни науки](https://web.cs.toronto.edu/) на [University of Toronto](https://www.utoronto.ca/).

## Дефиниции
В рамките на задачата ще дефинираме следните понятия:

1. токени - отделните String-ове, които се получват при извикване на метода String.split("\\s+") върху даден текст

2. думи - непразни token-и, които не са изцяло съставени от пунктуационни знаци

3. изречения - поредици от символи, които отговарят на следните условия:
  - терминират се от символите !, ? или . (но не ги включва) или край на файла (End Of File)
  - изключват trailing и leading whitespaces
  - не са празни

4. фрази - непразни секции от изречение, които са разделени чрез запетайки [ , ], две точки [ : ] или точки-и-запетайки [ ; ]

## Feature-и
Ще използваме следните feature-и за идентифициране автора на даден текст:
- Средна дължина на думите - средният брой символи в дума, след strip-ване на пунктуацията.
- Тype-Token Ratio - броят на всички различни думи, използвани в текста, разделен на броя на всички думи. Измерва колко повтаряща се е лексиката.
- Hapax Legomena Ratio - броят на думите, срещащи се само по веднъж в даден текст, разделен на броя на всички думи.
- Среден брой думи в изречение - броят на всички думи, използвани в текста, разделен на броя на изреченията.
- Сложност на изречение - средният брой фрази в изречение

## Избор на най-добро сходство
За да можем да определим най-доброто сходство между непознат текст и вече съществуващи лингвистични подписи на автори, ще използваме следната формула:

![alt text](https://camo.githubusercontent.com/2092e1e276437299564d0bac6217c94d29182135/68747470733a2f2f692e696d6775722e636f6d2f614545684878452e6a7067 "Формула за сходство")

къдетo:
- a и b са два лингвистични подписа
- fi,x e стойността на feature i в подписа x
- wi е теглото, асоцирано с feature i