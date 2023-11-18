import re

#Основная функция для перевода
def JSON2XML2(inputFile, outputFile):
    # Читаем данные из JSON файла построчно в массив
    with open(inputFile, mode='r', encoding='utf8') as f:
        s = f.read()
    #Добавляем тег root и стандартную шапку
    s = '<?xml version="1.0" encoding="UTF-8" ?>\n<root>' + s[1:]
    # Получаем все заголоки словарей
    dicts = []
    for i in re.findall(pattern=r'(\")(.*)(\")(\s*\:\s*\{)', string=s):
        dicts.append(i[1])
    # Убираем запятые
    s = re.sub(pattern=r'},', repl=r'}', string=s)
    #Заменяем все закрывающие скобки на закрывающие теги
    ptr = 0
    stack = ['root']
    b = ''
    for i in range(len(s)):
        if s[i] == '}':
            b += f'</{stack[-1]}>'
            stack.pop()
            continue
        if s[i] == '{':
            stack.append(dicts[ptr])
            ptr += 1
        b += s[i]
    s = b
    # Заменяем заголовки словарей
    s = re.sub(pattern=r'(\")(.*)(\")(\s*\:\s*\{)', repl=r'<\2>', string=s)
    # Заменяем основные поля
    s = re.sub(pattern=r'(\")(.*)(\")(\s*\:\s*\"?)(.+?)(\"?\s*\,?\n)', repl=r'<\2>\5</\2>\n', string=s)
    # Записываем в файл
    output_file = open(outputFile, "w", encoding='utf8')
    output_file.write(s)

def main():
    JSON2XML2(inputFile='day.json', outputFile='day2.xml')

if __name__ == '__main__':
    main()

