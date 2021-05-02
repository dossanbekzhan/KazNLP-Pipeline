# KazNLP-Pipeline

Kazakh Natural Language Pipeline

##Software and hardware requirements

Software requirements:
* - recent OS released during last 5 years (Windows 10, Linux, MacOS)
* - Java  JDK 1.8+
* - libraries: Gson, json-simple, opennlp-tools, stanford-corenlp,

Hardware requirements:
* - hardware capable of running recent OS released during last 5 years (Windows 10, Linux, MacOS)

### How to use the monolingual NLP tools


Repository consist following directories:

* resources/ - resource files in *.json java/core - папка ішінде қазақ тілліндегі мәтіндерді алдын ала өңдеуге қажетті
класстарды consist.

* java/ classes for tokenizing, morphology analyzing, word form generating, and etc. It consist following files:
MorphAnalyzer.java SentenceSplitter.java Tokenizer.java WordForm.java

_**The sequence of steps for launching files is as follows:**_
* MorphAnalyzer - морф анализатор для каз языка. Переменная text с типом String принимает текст на каз языке, и возвращает
морф разбор в json формате.
* SentenceSplitter - разделяет предложений на sentences. Переменная text с типом String
принимает текст на каз языке, и разбивает на предложений. 
* Tokenizer - разбивает необработанный текст на слова,
предложения, называемые токенами. Переменная text с типом String принимает текст на каз языке, и возвращает список
токенов.
* WordForm - построение грамматических форм на основе заданного слова. Переменная word с типом String принимает
слово на каз языке и тип(Noun or Verb). В качестве ответа получим всевозможные формы данного слова в формате json.
