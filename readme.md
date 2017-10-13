# epub-konjac 

[![Build Status](https://travis-ci.org/ynojima/epub-konjac.svg?branch=master)](https://travis-ci.org/ynojima/epub-konjac)

epub-konjac is an utility to translate epub books. Since Microsoft Translation API is used, valid subscription key is required.

## Building

```
./gradlew bootJar
```

## Configuration

place application.yml in the same directory of epub.konjac.jar

#### application.yml

```
msTranslator:
  client:
    subscriptionKey: <fill your subscription key here>
  language:
    source: en        # default source language
    destination: ja   # default destination language
```

## Running

```
java -jar epub-konjac.jar --src=<path to source epub file> [--dst=<path to destination epub file>] \
[--srcLang=<source language>] [--dstLang=<destination language>]
```
