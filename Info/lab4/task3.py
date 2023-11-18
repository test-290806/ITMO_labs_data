import re
#Размер табуляции
tabVal = '  '
#Стандартный текст в начале
standartStr = '<?xml version="1.0" encoding="UTF-8" ?>'
#Словарь со специальными символами которые необходимо заменять
specialSymbolsDict = {
    '<': '&lt;',
    '>': '&gt;',
    '&': '&amp;',
    '"': '&quot;',
    "'": "&apos;"
}
#Список со всеми спецсимволами
allSpecialSymbols = ':! "#$%&\'()*+,/;<=>? @[\]^`{|}~'

ind = 0
#Символы которые можно пропускать
separators = [',', ' ', ':', '\n']

#Получение строки
def getString(s):
    global ind
    #Скип открывающих ковычек
    ind += 1
    #Ищем первое строку с помощью регулярки обрабатываея жкранирование
    res = re.search(r'(.*?[^\\])(\")', s[ind:]).groups()[0]
    #Смещаем индекс для корректной дальнейшей работы
    ind += len(res) + 1
    return res
#Получение литерала
def getLiteral(s):
    global ind
    #Ищем первое строку с помощью регулярки обрабатываея жкранирование
    res = re.search(r'(\w*)(\}|\]|\,|$)', s[ind:]).groups()[0]
    #Смещаем индекс для корректной дальнейшей работы
    ind += len(res)

    if res == 'true': return True
    if res == 'false': return False
    if res == 'null': return None
#Получение целого числа
def getNum(s):
    global ind
    # Ищем первое строку с помощью регулярки обрабатываея жкранирование
    res = re.match(r'(.+?)(\}|\]|\,|$)', s[ind:]).groups()[0]
    # Смещаем индекс для корректной дальнейшей работы
    ind += len(res)
    #nan
    if res == 'NaN':
        return float('nan')
    #inf
    if res == 'Infinity':
        return float('inf')
    if res == '-Infinity':
        return -float('inf')
    #Целое число
    if re.fullmatch(r'\-?\d+', res):
        return int(res)
    return float(res)

#Получение словаря из json строки
def getDict(s):
    global ind
    dict = {}
    while ind < len(s):
        skipSeps(s)
        #Конец словаря
        if s[ind] == '}':
            ind += 1
            break
        skipSeps(s)
        #Получаем имя ключа
        key = getString(s)
        #Получаем значние
        dict[key] = parse(s)
    return dict
#Получение списка из json строки
def getList(s):
    global ind
    list = []
    while ind < len(s):
        # Конец списка
        if s[ind] == ']':
            ind += 1
            break
        # Получаем значние
        list.append(parse(s))
    return list

#Определение чего надо парсить(для начала парсинга)
def parse(s):
    global ind
    #Пропускаем ненужные символы
    skipSeps(s)
    #Словарь
    if s[ind] == '{':
        ind += 1
        return getDict(s)
    #Список
    if s[ind] == '[':
        ind += 1
        return getList(s)
    # Ключ задает строковой значение
    if s[ind] == '"':
        return getString(s)
    # Ключ задает литерал
    if s[ind] in ['t', 'f', 'n']:
        return getLiteral(s)
    # Ключ задает число
    return getNum(s)
#Пропуск разделителей
def skipSeps(s):
    global ind
    while s[ind] in separators: ind += 1

#Проверяет строку на наличие специальных символов
def ckeckSpecialSymbols(s):
    if re.fullmatch(r'\d+\w+', s): return True
    if s[0] == '-': return True
    for c in allSpecialSymbols:
        if c in s:
            return True
    return False
#заменяет спецсимволы на читаемые XML форматом
def replaceSpecialSymbols(s):
    res = ''
    #Проверяем на особые символы
    for c in s:
        if c in specialSymbolsDict:
            res += specialSymbolsDict[c]
        else:
            res += c
    return res
#Нормализует название тега
def normalizeKey(s):
    #Убираем экранирование
    s = re.sub(r'([^\\])(\\)([^\\])', r'\1\3', s)
    s = re.sub(r'\\\\', r'\\', s)
    #Заменяем спецсимволы
    s = replaceSpecialSymbols(s)
    #Проверяем на число
    if re.fullmatch(r'\d*', s):
        return 'n' + s
    return s

def getTag(item, curTab, openTag, closeTag):
    tab = tabVal * curTab
    # Строка
    if type(item) == type(''):
        return f'{tab}<{openTag}>{replaceSpecialSymbols(item)}</{closeTag}>\n'
    #Число
    if type(item) == type(0) or type(item) == type(0.0):
        return f'{tab}<{openTag}>{item}</{closeTag}>\n'
    # Словрь
    if type(item) == type({}):
        return f'{tab}<{openTag}>\n{dictToXml(item, curTab + 1)}{tab}</{closeTag}>\n'
    # Список
    if type(item) == type([]):
        return f'{tab}<{openTag}>\n{listToXML(item, curTab + 1)}{tab}</{closeTag}>\n'
    # литералы
    if item == True:
        return f'{tab}<{openTag}>True</{closeTag}>\n'
    if item == False:
        return f'{tab}<{openTag}>False</{closeTag}>\n'
    if item == None:
        return f'{tab}<{closeTag}/>\n'

#Переводит список в строку формата XML
#На вход получает словарь и текущий отступ
def dictToXml(dict, curTab):
    res = ''

    for item in dict:
        #Определяем имя тега
        openTag = item
        closeTag = item
        #Проводим проверку на спецсимволы
        if ckeckSpecialSymbols(item):
            openTag = f'key name="{normalizeKey(item)}"'
            closeTag = 'key'

        res += getTag(dict[item], curTab, openTag, closeTag)
    return res

#Переводит список в строку формата XML
def listToXML(list, curTab):
    res = ''

    for item in list:
        res += getTag(item, curTab, 'item', 'item')

    return res

#Основная функция для перевода
def JSON2XML3(inputFile, outputFile):
    # Читаем данные из JSON файла построчно в массив
    with open(inputFile, mode='r', encoding='utf8') as f:
        s = f.read()
    #Перводим глобальный индекс на второй символ строки
    global ind
    ind = 0
    # Парсим строку в словарь
    dict = parse(s)
    # Добавляем тег root
    dict = {'root': dict}
    # Переводим словарь в XML
    res = f'{standartStr}\n{dictToXml(dict, 0)}'
    # Записываем в файл
    output_file = open(outputFile, "w", encoding='utf8')
    output_file.write(res)

def main():
    JSON2XML3(inputFile='day.json', outputFile='day3.xml')

if __name__ == '__main__':
    main()
