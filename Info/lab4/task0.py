#Основная функция для перевода
def JSON2XML0(inputFile, outputFile):
    # Читаем данные из JSON файла построчно в массив
    with open(inputFile, mode='r', encoding='utf8') as f:
        lines = f.readlines()
    res = '<?xml version="1.0" encoding="UTF-8" ?>\n<root>\n'
    stack = ['root']
    for line in lines[1:]:
        #Запоминаем пробелы
        tab = ''
        for i in line:
            if i != ' ':
                break
            tab += i
        #Убираем пробелы
        s = line.strip()
        #Закрывающие тег
        if s[0] == '}':
            res += f'{tab}</{stack[-1]}>\n'
            stack.pop()
            continue
        #Получаем ключ
        key = ''
        ind = 1
        while s[ind] != '"':
            key += s[ind]
            ind += 1
        ind += 3
        #Открывающий тег
        if s[ind] == '{':
            res += f'{tab}<{key}>\n'
            stack.append(key)
            continue
        #Получаем значение одинрного тега
        val = ''
        ind += 1
        while s[ind] != '"':
            val += s[ind]
            ind += 1
        res += f'{tab}<{key}>{val}</{key}>\n'

    # Записываем в файл
    output_file = open(outputFile, "w", encoding='utf8')
    output_file.write(res)

def main():
    JSON2XML0(inputFile='day.json', outputFile='day0.xml')

if __name__ == '__main__':
    main()





